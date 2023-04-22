package com.greff.foodapi.domain.usecase.exception;

//inheriting NotFoundObjectException, starting to create a hierarchy of exceptions
//it's cool because state not found it's an object not found
//since this heritage already got annotation not found, we don't need to put, but if you want to be more specific should use it
public class StateNotFoundException extends NotFoundObjectException {

    public StateNotFoundException(String message) { //receive msg into constructor type and pass to super constructor, parent that got extended
        super(message);
    }
}
