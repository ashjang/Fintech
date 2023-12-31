package com.ashjang.account.controller;

import com.ashjang.account.domain.dto.AccountDto;
import com.ashjang.account.domain.dto.AddAccountForm;
import com.ashjang.account.domain.dto.AccountForm;
import com.ashjang.account.domain.model.Account;
import com.ashjang.account.exception.CustomException;
import com.ashjang.account.exception.ErrorCode;
import com.ashjang.account.service.AccountManageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountManageController {
    private final AccountManageService accountManageService;

    @ApiOperation(value = "계좌 생성", response = AccountDto.class, notes = "bankType(은행종류): WOORI, HANA, NONGHYUP, SHINHAN, KAKAO\ntype(계좌유형): SAVINGS, INSTALLMENT_SAVINGS")
    @PostMapping("/creation")
    public ResponseEntity<AccountDto> createAccount(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                                    @Valid @RequestBody AddAccountForm form, BindingResult bindingResult) {
        // 폼 형식에 맞지 않음
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.VALIDATION_FAIL);
        }

        Account account = accountManageService.createAccount(token, form);
        return ResponseEntity.ok(AccountDto.from(account));
    }

    @ApiOperation(value = "계좌 삭제", response = AccountDto.class)
    @DeleteMapping("/deletion")
    public ResponseEntity<AccountDto> deleteAccount(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                                    @Valid @RequestBody AccountForm form, BindingResult bindingResult) {
        // 폼 형식에 맞지 않음
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.VALIDATION_FAIL);
        }

        Account account = accountManageService.deleteAccount(token, form);
        return ResponseEntity.ok(AccountDto.from(account));
    }

    @ApiOperation(value = "계좌 조회", response = AccountDto.class)
    @PostMapping
    public ResponseEntity<AccountDto> selectAccount(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                                    @Valid @RequestBody AccountForm form, BindingResult bindingResult) {
        // 폼 형식에 맞지 않음
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.VALIDATION_FAIL);
        }

        Account account = accountManageService.selectAccount(token, form);
        return ResponseEntity.ok(AccountDto.from(account));
    }
}
