package com.expeditors.ecommerce.utils;

public class JWTExpiredException extends RuntimeException {
    public JWTExpiredException(String message) {
        super(message);
    }
}
