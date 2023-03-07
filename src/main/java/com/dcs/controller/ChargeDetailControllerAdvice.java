package com.dcs.controller;

import com.dcs.exception.ChangeDetailRecordExistsWithinDateRangeForVehicle;
import com.dcs.exception.ChargeDetailRecordByIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ChargeDetailControllerAdvice {
    @ExceptionHandler(ChargeDetailRecordByIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleChargeDetailRecordByIdNotFoundException(
            Exception e) {
        return new ResponseEntity<>(
                ErrorResponse.create(
                        e,
                        HttpStatus.NOT_FOUND,
                        e.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ChangeDetailRecordExistsWithinDateRangeForVehicle.class)
    public ResponseEntity<ErrorResponse> handleChargeDetailRecordOverlappingException(
            Exception e) {
        return new ResponseEntity<>(
                ErrorResponse.create(
                        e,
                        HttpStatus.BAD_REQUEST,
                        e.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
