package com.greff.foodapi.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.greff.foodapi.api.model.mixin.RestaurantMixin;
import com.greff.foodapi.domain.model.Restaurant;
import org.springframework.context.annotation.Configuration;

@Configuration //need to be a spring container
public class JacksonMixinModule extends SimpleModule { //since I'm using module method to do it, need to inherit that class

    public JacksonMixinModule() {
        //this method is to say that entity class has a mix-in configuration class
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
    }
}
