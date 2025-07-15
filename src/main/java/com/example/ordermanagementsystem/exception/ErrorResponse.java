package com.example.ordermanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int code;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(LocalDateTime timestamp, int code, String error, String message, String path) {
        this.timestamp = timestamp;
        this.code = code;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Factory method to create an ErrorResponse instance.
     *
     * @param status  the HTTP status
     * @param message the error message
     * @param path    the request path
     * @return an ErrorResponse instance
     */

    public static ErrorResponse of(  HttpStatus status, String message, String path) {
        return new ErrorResponse(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            message,
            path
        );
    }
}

