package com.greff.foodapi.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private String[] values;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.values = constraintAnnotation.allowed();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        for (String contentType : values) if (value.getContentType().equals(contentType)) return true;

        return false;
    }
}
