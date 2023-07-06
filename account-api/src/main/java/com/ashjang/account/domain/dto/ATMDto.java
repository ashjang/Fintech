package com.ashjang.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ATMDto {
    private String accountNumber;
    private Long money;
    private Long changes;
    private String type;
    private LocalDateTime createdAt;
}
