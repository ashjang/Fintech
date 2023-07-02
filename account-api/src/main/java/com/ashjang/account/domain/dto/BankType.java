package com.ashjang.account.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum BankType {
    WOORI("001", "우리은행"),
    HANA("002", "하나은행"),
    NONGHYUP("003", "농협은행"),
    SHINHAN("004", "신한은행"),
    KAKAO("005", "카카오뱅크")
    ;

    private final String number;
    private final String name;

    private static final Map<String, BankType> BY_NAME =
            Stream.of(values()).collect(Collectors.toMap(BankType::getName, x -> x));

    public static BankType valueOfName(String name) {
        return BY_NAME.get(name);
    }
}
