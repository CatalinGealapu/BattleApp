package com.realgrowsoft.BattleApp.exception;

import org.springframework.http.HttpStatus;

public class BattleAppException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public BattleAppException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BattleAppException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
