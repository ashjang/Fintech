package com.ashjang.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
    @NotBlank
    private String phone;
    @Size(min = 5, max = 20)
    private String name;
    @Size(min = 14, max = 14)
    private String idNumber;
}
