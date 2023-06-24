package com.ashjang.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ALREADY_EXISTS_NICKNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    ALREADY_EXISTS_REGISTER(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
