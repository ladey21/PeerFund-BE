package com.peerfund.peerfundapp.exceptions;

import org.springframework.http.HttpStatus;

public class PeerFundException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public PeerFundException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public PeerFundException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public PeerFundException(HttpStatus status) {
        this.status = status;
    }
}
