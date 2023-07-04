package com.ashjang.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountForm {
    @NotNull
    private AccountType type;
    @NotBlank
    @Size(min = 4, max = 4)
    private String password;
    @NotNull
    private BankType bankType;
}
