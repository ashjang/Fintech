package com.ashjang.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ALREADY_EXISTS_NICKNAME("이미 존재하는 닉네임입니다."),
    ALREADY_EXISTS_REGISTER("이미 존재하는 회원입니다."),
    VALIDATION_FAIL("폼 형식에 맞게 작성해주세요."),

    SIGNIN_FAIL("아이디와 비밀번호를 다시 확인해주세요.")
    ;

    private final String detail;
}
