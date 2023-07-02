package com.ashjang.account.domain.model;

import com.ashjang.account.domain.dto.AccountType;
import com.ashjang.account.domain.dto.AddAccountForm;
import com.ashjang.account.domain.dto.BankType;
import com.ashjang.user.domain.model.Customer;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Account extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, unique = true)
    private String accountNumber;

    private Integer password;
    private Long balance;
    private AccountType type;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    private BankType bank;

    public static Account from(Customer userId, AddAccountForm form, String accountNumber) {
        return Account.builder()
                .accountNumber(accountNumber)
                .password(form.getPassword())
                .balance(0L)
                .type(form.getType())
                .customer(userId)
                .bank(BankType.valueOfName(form.getBankName()))
                .build();
    }
}