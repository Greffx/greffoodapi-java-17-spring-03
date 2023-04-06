package com.greff.foodapi.core.annotations;

import com.greff.foodapi.domain.model.enums.UrgentLevel;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) //define how much time will be running, RUNTIME will be read at action time, runtime
@Qualifier //this annotation class will be qualifier too
public @interface NotificationType {

    UrgentLevel value(); //standard value
}
