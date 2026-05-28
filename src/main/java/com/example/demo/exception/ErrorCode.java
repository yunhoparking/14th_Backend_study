package com.example.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_INPUT(HttpStatus.BAD_REQUEST,"입력값이 올바르지 않습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"서버 내부 오류 발생"),

    POST_NOT_FOUND(HttpStatus.NOT_FOUND,"게시글을 찾을 수 없습니다"),
    POST_ALREADY_EXISTS(HttpStatus.CONFLICT,"이미 존재하는 게시글"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"가입되지 않은 이메일입니다"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다"),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT,"이미 가입된 이메일입니다");

    private final HttpStatus status;
    private final String message;
}