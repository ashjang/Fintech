package com.ashjang.account.service;

import com.ashjang.account.domain.dto.ATMDto;
import com.ashjang.account.domain.dto.AccountForm;
import com.ashjang.account.domain.dto.AccountType;
import com.ashjang.account.domain.model.Account;
import com.ashjang.account.domain.repository.AccountRepository;
import com.ashjang.account.exception.CustomException;
import com.ashjang.account.exception.ErrorCode;
import com.ashjang.domain.common.UserVo;
import com.ashjang.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ATMService {
    private final AccountRepository accountRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    // 금액 인출
    @Transactional
    public ATMDto withdrawal(String token, Long money, AccountForm form) {
        if (!jwtAuthenticationProvider.isValidToken(token)) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        Account account = accountRepository.findByAccountNumberAndCustomer_Id(form.getAccountNumber(), userVo.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ACCOUNT));

        // 계좌유형(적금)일 때,
        if (account.getType() == AccountType.INSTALLMENT_SAVINGS) {
            throw new CustomException(ErrorCode.CHECK_ACCOUNT_TYPE);
        }
        // 입력 계좌 비밀번호 일치여부
        if (!account.getPassword().equals(form.getAccountPassword())) {
            throw new CustomException(ErrorCode.CHECK_PASSWORD);
        }
        // 잔액이 부족할 때
        if (account.getBalance() - money < 0) {
            throw new CustomException(ErrorCode.CHECK_MONEY);
        }

        account.setBalance(account.getBalance() - money);

        return new ATMDto(
                account.getAccountNumber(),
                money,
                account.getBalance(),
                "금액 인출",
                LocalDateTime.now()
        );
    }
}
