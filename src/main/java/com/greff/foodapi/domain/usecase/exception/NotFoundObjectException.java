package com.greff.foodapi.domain.usecase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

//ResponseStatusException this exception is from springframework, it's a runTimeException too
//in hierarchy classes, ResponseStatusException got subclasses that we can use for which response that we need
public class NotFoundObjectException extends ResponseStatusException {

    public NotFoundObjectException(HttpStatusCode status, String reason) { //created a constructor that receives 2 params
        super(status, reason); //and repass to super constructor, which is from parent class
    }

    public NotFoundObjectException(String message) {
        this(HttpStatus.NOT_FOUND, message); //this will call line 12
        //if this class got instanced and get this constructor right here the status code will be not found by default but can inform another too
    }
}
