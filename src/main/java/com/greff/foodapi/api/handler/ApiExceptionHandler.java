package com.greff.foodapi.api.handler;

import com.greff.foodapi.domain.usecase.exception.BusinessException;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
//this annotation means that can add exception handlers which all project exceptions will be treated in here
//center point to treat them will be this class
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    //don't need to treat all internal exceptions spring MVC
    // springResponseEntityExceptionHandler this class already do that for us, can be inherited by exception global classes like mine
    //and treat all internal exceptions spring MVC

    //treat this exception, captures with this annotation
    //when capture this one, will get all of them, like cityNotFound, state, restaurant, because of hierarchy chain, gets all subclasses
    @ExceptionHandler(NotFoundObjectException.class)
    public ResponseEntity<Object> handleNotFoundObjectException(NotFoundObjectException ex, WebRequest request) { //start with 'handle'  exceptions method name, to follow pattern
        // WebRequest can be received as param method, that way request come auto and spring passes which request it was
        //when capture with annotation, this method will be called auto, passing exception thrown like a param
        //we can use responseEntity to customize response, body, headers with it

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTITY_NOT_FOUND;
        String detail = ex.getMessage();

        ProblemDetails problemDetails = createProblemDetailsBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
        //ideal to all methods, handlers return something like this, handleExceptionInternal is a center point to return customized response
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = ex.getMessage();

        ProblemDetails problemDetails = createProblemDetailsBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<Object> handleEntityInUseException(EntityInUseException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        String detail = ex.getMessage();

        ProblemDetails problemDetails = createProblemDetailsBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @Override //type of error when JSON got something wrong, like a ',() {}' in wrong place or something like that or string instead of integer when needed an integer
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.INVALID_MESSAGE;
        String detail = "Response body is invalid, verify sintaxe error"; // ex.getMessage() got many sensitive data, means that's better that I create another one

        ProblemDetails problemDetails = createProblemDetailsBuilder(HttpStatus.valueOf(status.value()), problemType, detail).build();

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @Override
    //protected method of abstract class ResponseEntityExceptionHandler, to return something to every spring MVC exception
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (body == null) { //to not substitute personalized message that I did in each exception, created by me
            body = ProblemDetails.builder()
                    .title(HttpStatus.valueOf(statusCode.value()).getReasonPhrase()) //describes little about statusCode, here is substituting that customized exception message
                    .status(statusCode.value()) //getting value of http status
                    .build(); //customizing body
        } else if (body instanceof String bodyString) { //checking if body is an instance of bodyString
            body = ProblemDetails.builder()
                    .title(bodyString) //taking string that exception returned and changing into an exception default message
                    .status(statusCode.value())
                    .build(); //if body is an instance of string, will build a problem and turn into a standard exception
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ProblemDetails.ProblemDetailsBuilder createProblemDetailsBuilder(HttpStatus status, ProblemType problemType, String detail) { //method to instance a builder
        //will not instance a build(); only builder of problemDetails, build must be done in method, case that need something else, can add in there
        return ProblemDetails.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
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
