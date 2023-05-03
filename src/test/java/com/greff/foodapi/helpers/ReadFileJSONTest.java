package com.greff.foodapi.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greff.foodapi.domain.model.Kitchen;

import java.io.File;
import java.io.IOException;

public class ReadFileJSONTest { //just a helper to transform JSON file to Objects to make test classes easiest to read
    //reads files like kitchen-request-instance

    static ObjectMapper objectMapper = new ObjectMapper();

    public static Kitchen kitchenJsonFileReader() throws IOException { //can be static, so just call class and method
        return objectMapper.readValue(new File("src/test/resources/kitchens/kitchen-request-instance.json"), Kitchen.class);
    }
}
