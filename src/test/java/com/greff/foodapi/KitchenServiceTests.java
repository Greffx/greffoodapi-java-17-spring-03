package com.greff.foodapi;

import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.usecase.KitchenService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class KitchenServiceTests {

    @Autowired
    private KitchenService kitchenService; //can't have an injection by constructor in here, just with annotation type

    @Test
    void shouldCreateKitchenWithSuccessTest() { //always follow a pattern, like start method name with 'should'
        //this one is more like a happy path to validate, when all go alright
        //it's good for tests to follow like:
        //situation
        Kitchen kitchen = new Kitchen();
        kitchen.setName("Chinese"); //creating an instance of kitchen

        //action
        kitchen = kitchenService.create(kitchen); //going throw action of creation method

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
}
