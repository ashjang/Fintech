package com.ashjang.account.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    SAVINGS("예금", "1"),
    INSTALLMENT_SAVINGS("적금", "2")
    ;

    private final String type;
    private final String number;
}
