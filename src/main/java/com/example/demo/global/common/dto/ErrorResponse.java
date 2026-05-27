package com.example.demo.global.common.dto;

import com.example.demo.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int status;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.message = errorCode.getMessage();
    }
}