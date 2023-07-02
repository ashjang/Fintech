package com.ashjang.account.domain.dto;

import com.ashjang.account.domain.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDto {
    private String accountNumber;
    private Integer password;
    private Long balance;
    private AccountType type;
    private BankType bank;

    public static AccountDto from(Account account) {
        return new AccountDto(
                account.getAccountNumber(),
                account.getPassword(),
                account.getBalance(),
                account.getType(),
                account.getBank()
        );
    }
}
