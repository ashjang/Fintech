package com.ashjang.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChangeDetailForm {
    private String password;
    private String againPassword;
    private String phone;
    private String name;
}
