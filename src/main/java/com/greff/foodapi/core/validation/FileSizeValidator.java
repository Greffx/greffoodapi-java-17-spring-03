package com.greff.foodapi.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

//will implement a validator using interface ConstraintValidator which needs an annotation class to validate a generic type, like MultipartFile
public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    //DataSize spring class that represents a data size, util to parse kb to mb or gb or something like that
    private DataSize maxSize;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        this.maxSize = DataSize.parse(constraintAnnotation.max());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value == null || value.getSize() <= this.maxSize.toBytes();
        //getSize() from MultipartFile return in bytes, that's why needs to compare maxsize in bytes
    }
}
