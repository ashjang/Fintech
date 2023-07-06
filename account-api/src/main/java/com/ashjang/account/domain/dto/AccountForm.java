package com.ashjang.account.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountForm {
    @NotBlank
    private String accountNumber;
    @NotBlank
    private String accountPassword;
}
