package com.greff.foodapi.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter //will have all methods getters for attributes, like getMessage
@Builder //project standard, can't be instanced like new ClassName(), needs to use another way to build object
//would be like StandardProblem.something().something().builder(), still will be a constructor
@JsonInclude(JsonInclude.Include.NON_NULL) //annotation to not add if field is null
public class ProblemDetails {
    //this is RFC 7807 format, problem details for HTTP APIS
    //this an outer class
    //this class is more like a response API, that's why a changed package

    //STANDARD properties of specifications from problem details RFC 7807

    private Integer status; //will return status code like 1xx, 2xx, 3xx, 4xx, 5xx
    private String type; //URI that is a simple explanation to exception, could be URL saying more about problem and how to fix it
    private String title; //fast description of problem type, like a title, need to be easy to user understanding, title is always the same
    private String detail; //specific description about error, more detailed description, detail can change, like if id 20 is not found or 25, will change detail
    //detail is more to programmer, way to technical for user

    //we can expand to more personalized properties, like a specification
    private String userMessage; //similar to detail, but easiest to user understanding
    private OffsetDateTime timestamp;

    private List<Field> fields;

    @Builder
    @Getter
    public static class Field { //Nested classe, this an inner class

        private String fieldName;
        private String defaultMessage;
    }

}
