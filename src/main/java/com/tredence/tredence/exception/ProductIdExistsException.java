package com.tredence.tredence.exception;

public class ProductIdExistsException extends RuntimeException {

    public ProductIdExistsException(String message) {
        super(message);
    }

    public ProductIdExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
