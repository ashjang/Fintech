package com.ashjang.account.service;

import com.ashjang.account.domain.dto.AccountType;
import com.ashjang.account.domain.dto.AddAccountForm;
import com.ashjang.account.domain.dto.BankType;
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

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountManageService {
    private final AccountRepository accountRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerRepository customerRepository;

    // 계좌 생성
    public Account createAccount(String token, AddAccountForm form) {
        if (!jwtAuthenticationProvider.isValidToken(token)) {
            throw new CustomException(ErrorCode.NOT_VALID_TOKEN);
        }

        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        Customer customer = customerRepository.findByIdAndNickname(userVo.getId(), userVo.getNickname())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        // 은행 존재여부 확인
        if (BankType.valueOfName(form.getBankName()) == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_BANK);
        }

        // 계좌번호 만들기
        String accountNumber = setAccountNumber(customer.getId(), BankType.valueOfName(form.getBankName()), form.getType());

        return Account.from(customer, form, accountNumber);
    }

    // 계좌번호 랜덤 생성
    public String setAccountNumber(Long id, BankType bankType, AccountType accountType) {
        // 앞 5자리
        String head = accountType.getNumber() + bankType.getNumber() + "-";
        // 앞 4자리 (id를 이용하여)
        String body = id % 100 + "-";
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

}
