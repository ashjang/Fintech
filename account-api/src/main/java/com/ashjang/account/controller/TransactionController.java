package com.ashjang.account.controller;

import com.ashjang.account.domain.dto.TransactionDto;
import com.ashjang.account.domain.dto.TransactionForm;
import com.ashjang.account.domain.model.Transaction;
import com.ashjang.account.exception.CustomException;
import com.ashjang.account.exception.ErrorCode;
import com.ashjang.account.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @ApiOperation(value = "계좌 이체", response = TransactionDto.class, notes = "100원 이상 거래 가능")
    @PutMapping
    public ResponseEntity<TransactionDto> transferMoney(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                                        @Valid @RequestBody TransactionForm form, BindingResult bindingResult) {
        // 폼 형식에 맞지 않음
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.VALIDATION_FAIL);
        }

        Transaction transaction = transactionService.transferMoney(token, form);
        return ResponseEntity.ok(TransactionDto.from(transaction));
    }

    @ApiOperation(value = "계좌 내역", response = List.class, notes = "입출금 조회")
    @GetMapping("/history")
    public ResponseEntity<List<TransactionDto>> transactionHistory(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                                @RequestParam String accountNumber) {
        List<Transaction> transactions = transactionService.historyTransaction(token, accountNumber);
        List<TransactionDto> result = transactions.stream().map(TransactionDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
