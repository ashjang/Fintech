package com.ashjang.account.domain.repository;

import com.ashjang.account.domain.dto.AccountType;
import com.ashjang.account.domain.dto.BankType;
import com.ashjang.account.domain.model.Account;
import com.ashjang.user.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findAllByCustomer_IdAndTypeAndBank(Long customerId, AccountType type, BankType bank);

    Optional<Account> findByAccountNumberAndCustomer_Id(String accountNumber, Long customerId);
}
