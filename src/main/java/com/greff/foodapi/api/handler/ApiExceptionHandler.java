package com.greff.foodapi.api.handler; //in this class I'm going to use ProblemDetails specifications to better handle some exceptions

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.greff.foodapi.domain.usecase.exception.BusinessException;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
//this annotation means that can add exception handlers which all project exceptions will be treated in here
//center point to treat them will be this class
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String GENERAL_ERROR_MESSAGE_FINAL_USER =
            "Internal error unexpected, try again and if happens the same problem, contact system administrator.";

    private MessageSource messageSource; //interface to fix messages

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //don't need to treat all internal exceptions spring MVC
    // springResponseEntityExceptionHandler this class already do that for us, can be inherited by exception global classes like mine
    //and treat all internal exceptions spring MVC

    //treat this exception, captures with this annotation
    //when capture this one, will get all of them, like cityNotFound, state, restaurant, because of hierarchy chain, gets all subclasses
    @ExceptionHandler(NotFoundObjectException.class)
    public ResponseEntity<Object> handleNotFoundObjectException(NotFoundObjectException ex, WebRequest request) { //start with 'handle'  exceptions method name, to follow pattern
        // WebRequest can be received as param method, that way request come auto and spring passes which request it was, represents web request, like get, post and such
        //when capture with annotation, this method will be called auto, passing exception thrown like a param
        //we can use responseEntity to customize response, body, headers with it

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();

        ProblemDetails problemDetails = createProblemDetailsBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
        //ideal to all methods, handlers return something like this, handleExceptionInternal is a center point to return customized response
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = ex.getMessage();

        ProblemDetails problemDetails = createProblemDetailsBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<Object> handleEntityInUseException(EntityInUseException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        String detail = ex.getMessage();

        ProblemDetails problemDetails = createProblemDetailsBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    //method to treat every exception that got no treat in another method, since exception is parent of them, we can use this way
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaughtException(Exception ex, WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.INTERNAL_ERROR;

        ProblemDetails problemDetails = createProblemDetailsBuilder(status, problemType, GENERAL_ERROR_MESSAGE_FINAL_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);

    }

    //type of error when JSON got something wrong, like a 'localhost/restaurant/1s' using an id long as a string
    //but this is a general method, if is an instance of a child class, will throw others classes
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException subEx)
            return handleMethodArgumentTypeMismatchException(subEx, headers, status, request);

        ProblemType problemType = ProblemType.INVALID_PARAMETER;
        String detail = "URL param is invalid, verify syntax error";

        ProblemDetails problemDetails = createProblemDetailsBuilder(HttpStatus.valueOf(status.value()), problemType, detail)
                .userMessage(GENERAL_ERROR_MESSAGE_FINAL_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    //exception thrown when a parameter of URL of body is wrong type
    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException subEx, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.INVALID_PARAMETER;
        String detail = String.format("Parameter '%s', with value '%s' is a invalid type. Try again using %s type", subEx.getParameter().getParameterName(),
                subEx.getValue(), Objects.requireNonNull(subEx.getRequiredType()).getSimpleName());

        ProblemDetails problemDetails = createProblemDetailsBuilder(HttpStatus.valueOf(status.value()), problemType, detail)
                .userMessage(GENERAL_ERROR_MESSAGE_FINAL_USER)
                .build();

        return handleExceptionInternal(subEx, problemDetails, headers, status, request);

    }

    //type of error when JSON got something wrong, like a ',() {}' in wrong place or something like that or string instead of integer when needed an integer
    //but this is a general method, if is an instance of a child class, will throw others classes
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex); //goes through entire exception stack and get root exception, root cause

        //if root cause is an instance of invalidFormat... will return another method
        if (rootCause instanceof InvalidFormatException subEx)
            return handleInvalidFormatException(subEx, headers, status, request);
        if (rootCause instanceof PropertyBindingException subEx)
            return handlePropertyBindingException(subEx, headers, status, request);

        ProblemType problemType = ProblemType.INVALID_MESSAGE;
        String detail = "Response body is invalid, verify syntax error"; // ex.getMessage() got many sensitive data, means that's better that I create another one

        ProblemDetails problemDetails = createProblemDetailsBuilder(HttpStatus.valueOf(status.value()), problemType, detail)
                .userMessage(GENERAL_ERROR_MESSAGE_FINAL_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    //exception is when using in request body property that's unknown or ignored
    private ResponseEntity<Object> handlePropertyBindingException(
            PropertyBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String path = ex.getPath().stream().map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.INVALID_MESSAGE;
        String detail = String.format("Property '%s' is not allowed to be used", path);

        ProblemDetails problemDetails = createProblemDetailsBuilder(HttpStatus.valueOf(status.value()), problemType, detail)
                .userMessage(GENERAL_ERROR_MESSAGE_FINAL_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    //type of error when JSON got something wrong, like string type instead of integer type, string id instead of long id
    private ResponseEntity<Object> handleInvalidFormatException(
            InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String path = ex.getPath().stream().map(reference -> reference.getFieldName()) //JsonMappingException using this one to use getFieldName method
                .collect(Collectors.joining(".")); //reduce to a string, would be like 'kitchen.id', you got something like kitchen id and join them

        ProblemType problemType = ProblemType.INVALID_MESSAGE;
        String detail = String.format("Property '%s', received value '%s', which is invalid type. Try again and use value that is equal to %s",
                path, ex.getValue(), ex.getTargetType().getSimpleName()); //altering this detail because it could have sensitive data, if I use default by exception type

        ProblemDetails problemDetails = createProblemDetailsBuilder(HttpStatus.valueOf(status.value()), problemType, detail)
                .userMessage(GENERAL_ERROR_MESSAGE_FINAL_USER) //since this one is not standard, that how's going to be passed in builder, it's more for user
                .build();

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    @Override
    //exception when there's no exception to handle, like when there's a URL error, throw nothing, so this one will handle this situation
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = String.format("Resource %s is a invalid, don't exist", ex.getRequestURL());

        ProblemDetails problemDetails = createProblemDetailsBuilder(HttpStatus.valueOf(status.value()), problemType, detail)
                .userMessage(GENERAL_ERROR_MESSAGE_FINAL_USER)
                .build();

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    @Override//exception when a field is null or is missing a field
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "One or more invalid fields, try again";

        //inside MethodArgumentNotValidException there's getBindingResult holds constraints violations of bean validation, which means
        //gather fields, properties was violated
        BindingResult bindingResult = ex.getBindingResult();

        //this is how to instance a list of inner class, nested class
        //this is mapping each fieldError type and building an Inner class Field type and returning a list of it
        //builder receives a property name and default message
        List<ProblemDetails.Field> fields = bindingResult.getFieldErrors().stream()
                .map(fieldError -> { //create this code block {} because of message var, returning bundle resource message type of messages.properties
                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                    //MessageSourceResolvable is the first param and bindingResult.getFieldErrors() is a type of that first param, comes from FieldError class that's an implementation of it
                    //second param is a locale, which is an object that represents a region like portuguese, english or etc.
                    //LocaleContextHolder.getLocale() takes a locale from operational system that you use as default

                    return ProblemDetails.Field.builder()
                            .fieldName(fieldError.getField())
                            .defaultMessage(message)
                            .build();
                })
                .toList();

        ProblemDetails problemDetails = createProblemDetailsBuilder(HttpStatus.valueOf(status.value()), problemType, detail)
                .timestamp(LocalDateTime.now())
                .userMessage(GENERAL_ERROR_MESSAGE_FINAL_USER)
                .fields(fields)
                .build();

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    @Override
    //protected method of abstract class ResponseEntityExceptionHandler, to return something to every spring MVC exception
    //ideal to all handlers to use this one to return something, like a center point to return, where can customize body response
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (body == null) { //if body comes null, because some exceptions don't get body, it will create one, to return something in response body
            body = ProblemDetails.builder()
                    //statusCode title,  like 'Not found', here is substituting that customized exception message
                    .title(HttpStatus.valueOf(statusCode.value()).getReasonPhrase())
                    .status(statusCode.value()) //getting value of http status
                    .timestamp(LocalDateTime.now())
                    .userMessage(GENERAL_ERROR_MESSAGE_FINAL_USER)
                    .build(); //customizing body
        } else if (body instanceof String bodyString) { //checking if body is an instance of bodyString, because it could be only a string like, if body would be 'ex.getMessage()', that body is only a string
            //this is because it's not good to return only a string, needs to be a complete body
            body = ProblemDetails.builder()
                    .title(bodyString) //taking string that exception returned and changing into an exception default message
                    .status(statusCode.value())
                    .timestamp(LocalDateTime.now())
                    .userMessage(GENERAL_ERROR_MESSAGE_FINAL_USER)
                    .build(); //if body is an instance of string, will build a problem and turn into a standard exception
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    //method to create a builder of problemDetails, instance ONLY A BUILDER, it isn't built yet, will come as a builder, not an instance of problemDetails
    private ProblemDetails.ProblemDetailsBuilder createProblemDetailsBuilder(
            HttpStatus status, ProblemType problemType, String detail) { //method to instance a builder
        //will not instance a build(); only builder of problemDetails, build must be done in method, case that need something else, can add in there
        return ProblemDetails.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .timestamp(LocalDateTime.now())
                .detail(detail);
    }

//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class) internal exception spring MVC
//    public ResponseEntity<Object> handleHttpMediaTypeNotSupportedException()
//        var standardProblem = StandardProblem.builder()
//                .message("Content type not supported or accepted")
//                .dateTime(LocalDateTime.now()).build()
//
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(standardProblem)
}