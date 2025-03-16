package com.loco.platform.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // jwt
    ILLEGAL_REGISTRATION_ID(NOT_ACCEPTABLE, "Illegal Registration ID"),
    INVALID_TOKEN(UNAUTHORIZED, "올바르지 않은 토큰입니다."),
    INVALID_JWT_SIGNATURE(UNAUTHORIZED, "잘못된 JWT 시그니처입니다."),
    TOKEN_EXPIRED(UNAUTHORIZED, "토큰이 만료되었습니다."),

    // user
    NOT_EXIST_USER(NOT_FOUND, "유저가 존재하지 않습니다."),

    // room
    BAD_USER_REQUEST(BAD_REQUEST, "작성자가 아니거나 존재하지 않습니다"),
    // room tags
    NOT_EXIST_ROOM_TAG(NOT_FOUND, "요청된 room tag가 존재하지 않습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
