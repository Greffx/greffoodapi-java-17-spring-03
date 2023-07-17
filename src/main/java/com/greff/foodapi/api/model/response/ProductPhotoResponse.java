package com.greff.foodapi.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoResponse {

    private String filename;
    private String description;
    private String contentType;
    private Long size;
}
