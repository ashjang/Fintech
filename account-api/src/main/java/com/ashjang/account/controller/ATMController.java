package com.ashjang.account.controller;

import com.ashjang.account.domain.dto.ATMDto;
import com.ashjang.account.domain.dto.AccountForm;
import com.ashjang.account.exception.CustomException;
import com.ashjang.account.exception.ErrorCode;
import com.ashjang.account.service.ATMService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("myMoney")
@RequiredArgsConstructor
public class ATMController {
    private final ATMService atmService;

    @ApiOperation(value = "금액 인출", response = ATMDto.class)
    @PutMapping
    public ResponseEntity<ATMDto> withdrawal(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                             @Valid @RequestBody AccountForm form, BindingResult bindingResult,
                                             @RequestParam Long money) {
        // 폼 형식에 맞지 않음
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.VALIDATION_FAIL);
        }

        ATMDto withdrawal = atmService.withdrawal(token, money, form);
        return ResponseEntity.ok(withdrawal);
    }
}
