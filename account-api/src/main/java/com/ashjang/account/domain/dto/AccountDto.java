package com.ashjang.account.domain.dto;

import com.ashjang.account.domain.model.Account;
import com.ashjang.user.domain.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDto {
    private String accountNumber;
    private String password;
    private Long balance;
    private AccountType type;
    private BankType bank;
    private Customer customer;

    public static AccountDto from(Account account) {
        return new AccountDto(
                account.getAccountNumber(),
                account.getPassword(),
                account.getBalance(),
                account.getType(),
                account.getBank(),
                account.getCustomerId()
        );
    }
}
