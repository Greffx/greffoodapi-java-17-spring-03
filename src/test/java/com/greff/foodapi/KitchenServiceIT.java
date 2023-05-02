package com.greff.foodapi;

import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.usecase.KitchenService;
import com.greff.foodapi.domain.usecase.exception.EntityInUseException;
import com.greff.foodapi.domain.usecase.exception.NotFoundObjectException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test; //Junit is a simple library to execute test code, can write API and integration tests too
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //supply with spring boot functions for tests
class KitchenServiceIT {
    //this is an integration test, but not API type, don't test like get endpoint for instance
    //difference between integration test and unit test is unit only test a class, integration tests class with injections
    //changing naming, saying that's another category of tests IT, integration type

    @Autowired
    private KitchenService kitchenService; //can't have an injection by constructor in here, just with annotation type

    @Test //every test method has this annotation, that's how is recognized as a test method
    void shouldCreateKitchenWithSuccess() { //always follow a pattern, like start method name with 'should'
        //this one is more like a happy path to validate, when all go alright
        //it's good for tests to follow like:
        //situation
        Kitchen kitchen = new Kitchen();
        kitchen.setName("Chinese"); //creating an instance of kitchen

        //action
        kitchen = kitchenService.create(kitchen); //going throw action of creation method

        //validation is good to check 1 function, ok to do more than 1 when got relation
        //validation
        assertThat(kitchen).isNotNull(); //validation if is null or not
        assertThat(kitchen.getId()).isNotNull();
    }

    @Test
    void shouldFailWhenTryToCreateAKitchenWithoutAName() {
        //need to test implementation behavior when all goes south, more like an unhappy path
        //but this confirms that implementation done what it should do

        Kitchen kitchen = new Kitchen();
        kitchen.setName("");

        //validation exception when trying to create a kitchen without a name, blank or null type
        //is been used bean validation in controller and entity, should throw a Constraint exception type
        assertThrows(ConstraintViolationException.class, () ->
                kitchenService.create(kitchen)
        );
    }

    @Test
    void shouldFailWhenTryToDeleteAKitchenInUse() {
        assertThrows(EntityInUseException.class, () -> kitchenService.deleteById(1L));
    }

    @Test
    void shouldFailWhenTryToDeleteAKitchenThatDoNotExist() {
        assertThrows(NotFoundObjectException.class, () -> kitchenService.deleteById(123L));
    }
}
