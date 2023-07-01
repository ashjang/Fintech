package com.ashjang.account.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    SAVINGS("예금", "1"),
    INSTALLMENT_SAVINGS("적금", "2")
    ;

    private final String type;
    private final String number;

    private static final Map<String, AccountType> BY_TYPE =
            Stream.of(values()).collect(Collectors.toMap(AccountType::getType, x -> x));

    public static AccountType valueOfNumber(String type) {
        return BY_TYPE.get(type);
    }
}
