package com.greff.foodapi.domain.usecase.exception;

//inheriting NotFoundObjectException, starting to create a hierarchy of exceptions
//it's cool because state not found it's an object not found
//since this heritage already got annotation not found, we don't need to put, but if you want to be more specific should use it
//that's what they call 'granularidade fina', that means more detailed type, rich in explanation
public class StateNotFoundException extends NotFoundObjectException {

    public StateNotFoundException(String message) { //receive msg into constructor type and pass to super constructor, parent that got extended
        super(message);
    }

    public StateNotFoundException(Long id) {
        this(String.format("State with id %d, not found", id)); //using this to not pass to parent constructor
    }
}
