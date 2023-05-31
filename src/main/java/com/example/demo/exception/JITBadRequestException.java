package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JITBadRequestException extends RuntimeException {

    public JITBadRequestException() {
    }

    public JITBadRequestException(String message) {
        super(message);
    }

    public JITBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}