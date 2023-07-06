package com.ashjang.account.domain.dto;

import com.ashjang.account.domain.model.Account;
import com.ashjang.user.domain.dto.CustomerDto;
import com.ashjang.user.domain.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AccountDto {
    private LocalDate createdAt;
    private String accountNumber;
    private Long balance;
    private AccountType type;
    private BankType bank;
    private CustomerDto customer;

    public static AccountDto from(Account account) {
        return new AccountDto(
                account.getCreatedAt().toLocalDate(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getType(),
                account.getBank(),
                CustomerDto.from(account.getCustomerId())
        );
    }
}
