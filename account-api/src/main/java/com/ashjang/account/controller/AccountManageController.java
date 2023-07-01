package com.ashjang.account.controller;

import com.ashjang.account.domain.dto.AccountDto;
import com.ashjang.account.domain.dto.AddAccountForm;
import com.ashjang.account.domain.model.Account;
import com.ashjang.account.service.AccountManageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountManageController {
    private final AccountManageService accountManageService;

    @ApiOperation(value = "계좌 생성", response = AccountDto.class)
    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                                    @RequestBody AddAccountForm form) {
        Account account = accountManageService.createAccount(token, form);
        return ResponseEntity.ok(AccountDto.from(account));
    }
}
