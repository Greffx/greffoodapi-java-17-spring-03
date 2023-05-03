package com.greff.foodapi.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greff.foodapi.domain.model.Kitchen;
import com.greff.foodapi.domain.model.Restaurant;

import java.io.File;
import java.io.IOException;

public class ReadFileJSONTest { //just a helper to transform JSON file to Objects to make test classes easiest to read
    //reads files like kitchen-request-instance

    static ObjectMapper objectMapper = new ObjectMapper();

    public static Kitchen kitchenJsonFileReader() throws IOException { //can be static, so just call class and method
        return objectMapper.readValue(new File("src/test/resources/kitchens/kitchen-request-instance.json"), Kitchen.class);
    }

    public static Restaurant restaurantJsonFileReader() throws IOException { //can be static, so just call class and method
        return objectMapper.readValue(new File("src/test/resources/restaurants/restaurant-request-instance.json"), Restaurant.class);
    }

    public static Restaurant restaurantNullFieldJsonFileReader() throws IOException { //can be static, so just call class and method
        return objectMapper.readValue(new File("src/test/resources/restaurants/restaurant-request-exception-instance.json"), Restaurant.class);
    }
}
