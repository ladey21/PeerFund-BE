package com.peerfund.peerfundapp.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message, HttpStatus badRequest) {
        super(message);
    }

    public BadRequestException(HttpStatus badRequest, String message){super(message);}

    public BadRequestException(String message) {
        super(message);
    }
}
