package com.expeditors.ecommerce.utils;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String details;

    public ErrorResponse(int statusCode, String message, LocalDateTime timestamp, String details) {
        this.statusCode = statusCode;
        this.message = details;
        this.timestamp = timestamp;
        this.details = message;
    }
}
