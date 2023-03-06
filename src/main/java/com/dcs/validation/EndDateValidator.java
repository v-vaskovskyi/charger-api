package com.dcs.validation;

import com.dcs.dto.ChargeDetailRecordDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EndDateValidator implements ConstraintValidator<ChargeDetailRecordStartDateBeforeLastDate, ChargeDetailRecordDto> {

    public boolean isValid(ChargeDetailRecordDto object, ConstraintValidatorContext context) {
        if (!(object instanceof ChargeDetailRecordDto)) {
            throw new IllegalArgumentException("@ChargeDetailRecordEndDate only applies to ChargeDetailRecordDto objects");
        }
        ChargeDetailRecordDto cdr = object;
        if (cdr.getStartTime() != null
            && cdr.getEndTime() != null
            && cdr.getEndTime().isAfter(cdr.getStartTime())) {
            return true;
        } else {
            return false;
        }
    }
}