//package com.greff.foodapi.core.validation;
//
////good to use when property got many rules and has used a lot of annotations and a lot of properties are using the same
////composed validation type
//
//import jakarta.validation.Constraint;
//import jakarta.validation.OverridesAttribute;
//import jakarta.validation.Payload;
//import jakarta.validation.constraints.PositiveOrZero;
//
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import static java.lang.annotation.ElementType.*;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//@Target({FIELD, METHOD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE}) //can use in method, attribute, constructor, that's what target annotation means
//@Retention(RUNTIME) //this one means that will read in runtime
//@Constraint(validatedBy = {}) //constraint, that means that which class that implements this validation
//@PositiveOrZero //will receive rules of this annotation
//public @interface DeliveryTax { //creating personalized annotation
//
//    @OverridesAttribute(constraint = PositiveOrZero.class, name = "message") //override 'message' of constraint and need to know parameter like 'message'
//    String message() default "{Invalid.DeliveryTax}"; //message key, which message will throw if constraint is necessary, which var
//
//    Class<?>[] groups() default { }; //this annotation enable to who's going to use this annotation, change group
//
//    Class<? extends Payload>[] payload() default { }; //payload is used for pass metadata
//}
