package com.ashjang.account.service;

import com.ashjang.account.domain.dto.AccountType;
import com.ashjang.account.domain.dto.AddAccountForm;
import com.ashjang.account.domain.dto.BankType;
import com.ashjang.account.domain.dto.AccountForm;
import com.ashjang.account.domain.model.Account;
import com.ashjang.account.domain.repository.AccountRepository;
import com.ashjang.account.exception.CustomException;
import com.ashjang.account.exception.ErrorCode;
import com.ashjang.domain.common.UserVo;
import com.ashjang.domain.config.JwtAuthenticationProvider;
import com.ashjang.user.domain.model.Customer;
import com.ashjang.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountManageService {
    private final AccountRepository accountRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerRepository customerRepository;

    // 계좌 생성
    @Transactional
    public Account createAccount(String token, AddAccountForm form) {
        if (!jwtAuthenticationProvider.isValidToken(token)) {
            throw new CustomException(ErrorCode.NOT_VALID_TOKEN);
        }

        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        Customer customer = customerRepository.findByIdAndNickname(userVo.getId(), userVo.getNickname())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        // 계좌 생성 가능한지 여부 확인(예금:1, 적금:2)
        if (form.getType() == AccountType.SAVINGS
                && accountRepository.findAllByCustomerIdAndTypeAndBank(customer, form.getType(), form.getBankType()).size() == 1) {
            throw new CustomException(ErrorCode.ALREADY_EXISTS_ACCOUNT);
        }
        if (form.getType() == AccountType.INSTALLMENT_SAVINGS
                && accountRepository.findAllByCustomerIdAndTypeAndBank(customer, form.getType(), form.getBankType()).size() == 2) {
            throw new CustomException(ErrorCode.ALREADY_EXISTS_ACCOUNT);
        }

        // 계좌번호 만들기
        String accountNumber = setAccountNumber(userVo.getId(), form.getBankType(), form.getType());
        return accountRepository.save(Account.from(customer, form, accountNumber));
    }

    // 계좌번호 랜덤 생성
    public String setAccountNumber(Long id, BankType bankType, AccountType accountType) {
        // 앞 5자리
        String head = accountType.getNumber() + bankType.getNumber() + "-";
        // 앞 4자리 (id를 이용하여)
        String body = String.format("%03d-", id % 100);
        // 나머지 6자리
        String tail = "";
        while (true) {
            Random random = new Random();
            tail = String.valueOf(random.nextInt(900000) + 100000);

            if (accountRepository.findByAccountNumber(head + body + tail).isEmpty()) {
                break;
            }
        }

        return head + body + tail;
    }

    // 계좌 삭제
    public Account deleteAccount(String token, AccountForm form) {
        if (!jwtAuthenticationProvider.isValidToken(token)) {
            throw new CustomException(ErrorCode.NOT_VALID_TOKEN);
        }

        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        Customer customer = customerRepository.findByIdAndNickname(userVo.getId(), userVo.getNickname())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        Account account = accountRepository.findByAccountNumberAndCustomerId(form.getAccountNumber(), customer)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ACCOUNT));

        // 잔액 확인
        if (account.getBalance() != 0) {
            throw new CustomException(ErrorCode.CHECK_BALANCE);
        }
        // 계좌번호 & 비번 일치 확인
        if (account.getAccountNumber().equals(form.getAccountNumber())
            && account.getPassword().equals(form.getAccountPassword())) {
            accountRepository.delete(account);
            accountRepository.flush();
        } else {
            throw new CustomException(ErrorCode.CHECK_PASSWORD);
        }

        return account;
    }

    // 계좌검색
    public Account selectAccount(String token, AccountForm form) {
        if (!jwtAuthenticationProvider.isValidToken(token)) {
            throw new CustomException(ErrorCode.NOT_VALID_TOKEN);
        }

        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        Customer customer = customerRepository.findByIdAndNickname(userVo.getId(), userVo.getNickname())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        Account account = accountRepository.findByAccountNumberAndCustomerId(form.getAccountNumber(), customer)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ACCOUNT));

        if (!account.getPassword().equals(form.getAccountPassword())) {
            throw new CustomException(ErrorCode.CHECK_PASSWORD);
        }

        return account;
    }
}
