package com.ashjang.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountForm {
    @NotBlank
    private String type;
    @NotBlank
    @Min(value = 1000)
    @Max(value = 9999)
    private Integer password;
    @NotBlank
    private String bankName;
}
