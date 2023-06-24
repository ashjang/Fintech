package com.ashjang.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    private String nickname;
    private String password;
    private String phone;
    private String name;
    private String id_number;
}
