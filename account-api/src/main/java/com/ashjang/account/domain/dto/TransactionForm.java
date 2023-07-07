package com.ashjang.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionForm {
    @Min(value = 100)
    private Long money;
    private String content;
    @NotBlank
    private String senderAccountNumber;
    @NotBlank
    private String senderAccountPassword;
    @NotBlank
    private String receiverAccountNumber;
}
