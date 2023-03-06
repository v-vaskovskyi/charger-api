package com.dcs.validation;

import com.dcs.dto.ChargeDetailRecordDto;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EndDateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChargeDetailRecordStartDateBeforeLastDate {
    String message() default "{message.startDate.before.lastDate}";
    Class<?>[] groups() default { };
    Class<? extends ChargeDetailRecordDto>[] payload() default { };
}