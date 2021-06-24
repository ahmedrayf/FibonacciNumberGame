package com.Fibonacci.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException {
    private final String message;

    public CustomException(String message) {
        this.message = message;
    }
}
