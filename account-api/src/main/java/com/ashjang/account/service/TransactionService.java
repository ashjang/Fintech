package com.ashjang.account.service;

import com.ashjang.account.domain.dto.AccountType;
import com.ashjang.account.domain.dto.TransactionForm;
import com.ashjang.account.domain.model.Account;
import com.ashjang.account.domain.model.Transaction;
import com.ashjang.account.domain.repository.AccountRepository;
import com.ashjang.account.domain.repository.TransactionRepository;
import com.ashjang.account.exception.CustomException;
import com.ashjang.account.exception.ErrorCode;
import com.ashjang.domain.common.UserVo;
import com.ashjang.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    // 이체
    @Transactional
    public Transaction transferMoney(String token, TransactionForm form) {
        if (!jwtAuthenticationProvider.isValidToken(token)) {
            throw new CustomException(ErrorCode.NOT_VALID_TOKEN);
        }

        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);

        Account sender = accountRepository.findByAccountNumberAndCustomer_Id(form.getSenderAccountNumber(), userVo.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Account receiver = accountRepository.findByAccountNumber(form.getReceiverAccountNumber())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ACCOUNT));

        // 송금자의 계좌 유형이 적금일 때,
        if (sender.getType() == AccountType.INSTALLMENT_SAVINGS) {
            throw new CustomException(ErrorCode.CHECK_ACCOUNT_TYPE);
        }
        // 송금자의 잔액 확인
        if (sender.getBalance() < form.getMoney()) {
            throw new CustomException(ErrorCode.CHECK_MONEY);
        }
        // 송금자 계좌 비밀번호 확인
        if (!sender.getPassword().equals(form.getSenderAccountPassword())) {
            throw new CustomException(ErrorCode.CHECK_PASSWORD);
        }

        sender.decreaseBalance(form.getMoney());
        receiver.increaseBalance(form.getMoney());
        return transactionRepository.save(Transaction.from(form, sender, receiver));
    }

    // 송금 조회
    public List<Transaction> historyTransaction(String token, String accountNumber) {
        if (!jwtAuthenticationProvider.isValidToken(token)) {
            throw new CustomException(ErrorCode.NOT_VALID_TOKEN);
        }

        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);

        Account account = accountRepository.findByAccountNumberAndCustomer_Id(accountNumber, userVo.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ACCOUNT));

        List<Transaction> transactionList = transactionRepository.findAllBySender_Id(account.getId());
        transactionList.forEach(x -> x.setMoney(-x.getMoney()));
        transactionList.addAll(transactionRepository.findAllByReceiver_Id(account.getId()));

        if (transactionList.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_TRANSACTION);
        }

        return transactionList;
    }
}
