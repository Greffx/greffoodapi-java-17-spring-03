package com.greff.foodapi.api.handler;

import com.greff.foodapi.domain.usecase.exception.BusinessException;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

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
    public ResponseEntity<Object> handleNotFoundObjectException(NotFoundObjectException ex, WebRequest request) {
        // WebRequest can be received as param method, that way request come auto and spring passes which request it was
        //when capture with annotation, this method will be called auto, passing exception thrown like a param
        //we can use responseEntity to customize response, body, headers with it

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        //ideal to all methods, handlers return something like this, handleExceptionInternal is a center point to return customized response
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<Object> handleEntityInUseException(EntityInUseException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override //protected method of abstract class ResponseEntityExceptionHandler, to return something to every spring MVC exception
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (body == null) { //to not substitute personalized message that I did in each exception, created by me
            body = StandardProblem.builder()
                    .message(HttpStatus.valueOf(statusCode.value()).getReasonPhrase()) //describes little about statusCode, here is substituting that customized exception message
                    .dateTime(LocalDateTime.now())
                    .build(); //customizing body

        } else if (body instanceof String bodyString) { //cheking if body is an instance of bodyString
            body = StandardProblem.builder()
                    .message(bodyString) //taking string that exception returned and changing into an exception default message
                    .dateTime(LocalDateTime.now())
                    .build(); //if body is an instance of string, will build a problem and turn into a standard exception
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }


//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class) internal exception spring MVC
//    public ResponseEntity<Object> handleHttpMediaTypeNotSupportedException()
//        var standardProblem = StandardProblem.builder()
//                .message("Content type not supported or accepted")
//                .dateTime(LocalDateTime.now()).build()
//
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(standardProblem)

}
