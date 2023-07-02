package com.ashjang.user.domain.dto;

import com.ashjang.user.domain.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String nickname;
    private String password;
    private String phone;
    private String name;
    private String idNumber;

    public static CustomerDto from(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getNickname(),
                customer.getPassword(),
                customer.getPhone(),
                customer.getName(),
                customer.getIdNumber()
        );
    }
}
