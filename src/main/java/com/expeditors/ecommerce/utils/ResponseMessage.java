package com.expeditors.ecommerce.utils;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResponseMessage<T> {
    private int statusCode;
    private String message;
    private T data;
    private String errorStack;

    public ResponseMessage(int statusCode, String message, T data, String errorStack) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.errorStack = errorStack;
    }
}
