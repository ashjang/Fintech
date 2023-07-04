package com.ashjang.account.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOT_VALID_TOKEN("로그인이 필요합니다."),
    NOT_FOUND_USER("사용자를 찾을 수 없습니다."),

    NOT_FOUND_BANK("등록된 은행이 없습니다."),
    NOT_FOUND_ACCOUNT_TYPE("등록된 계좌 유형이 없습니다."),
    VALIDATION_FAIL("폼 형식에 맞게 작성해주세요."),

    ALREADY_EXISTS_ACCOUNT("생성 가능한 계좌 개수가 초과되어 계좌 생성을 실패했습니다.")
    ;

    private final String detail;
}
