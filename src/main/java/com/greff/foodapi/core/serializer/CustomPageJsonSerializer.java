package com.greff.foodapi.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent //@JsonComponent is a spring component too, which offers serialize and deserialize implementation

//class to serialize with Jackson (jackson is the one it serializes java to json) a custom page
//jackson can also deserialize json to java
public class CustomPageJsonSerializer<T> extends JsonSerializer<Page<T>> {
    //needs to inherit this abstract class and indicates that is creating jsonSerializer for which type,
    //could be page, but page receives a type too, that's why I used generics


    @Override//receive as param, object page, with values
    public void serialize(Page<T> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        //means start, like '{}'
        gen.writeStartObject();
        //writing an object property with field name 'content' and its value is page values
        gen.writeObjectField("content", page.getContent());
        //another property to show in json response, will return a numeric property
        //this one would be page size, how many elements in that page
        gen.writeNumberField("size", page.getSize());
        //this one would be total elements in general
        gen.writeNumberField("total elements", page.getTotalElements());
        //this one would be total pages
        gen.writeNumberField("total pages", page.getTotalPages());
        //this one would be in which page is it now
        gen.writeNumberField("current page", page.getNumber());

        gen.writeEndObject();
    }
}
