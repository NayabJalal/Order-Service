package com.ecommerce.orderservice.exceptions;

public class InvalidOrderRequestException extends RuntimeException {
    public InvalidOrderRequestException(String message) {
        super(message);
    }
}

