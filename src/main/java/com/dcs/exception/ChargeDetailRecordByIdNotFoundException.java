package com.dcs.exception;

public class ChargeDetailRecordByIdNotFoundException extends RuntimeException {
    public ChargeDetailRecordByIdNotFoundException() {
        super();
    }

    public ChargeDetailRecordByIdNotFoundException(String message) {
        super(message);
    }
}
