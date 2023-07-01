package com.ashjang.account.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    SAVINGS("1"),
    INSTALLMENT_SAVINGS("2")
    ;

    private final String number;
}
