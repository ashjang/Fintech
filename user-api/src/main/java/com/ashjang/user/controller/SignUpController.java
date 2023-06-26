package com.ashjang.user.controller;

import com.ashjang.user.domain.SignUpForm;
import com.ashjang.user.domain.dto.CustomerDto;
import com.ashjang.user.exception.CustomException;
import com.ashjang.user.exception.ErrorCode;
import com.ashjang.user.service.customer.CustomerSignUpService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final CustomerSignUpService customerSignUpService;

    @ApiOperation(value = "고객용 회원가입", response = String.class)
    @PostMapping("/customer")
    public ResponseEntity<?> customerSignUp(@Valid @RequestBody SignUpForm signUpForm, BindingResult bindingResult) {
        // 폼 형식에 맞지 않음
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.VALIDATION_FAIL);
        }

        CustomerDto customerDto = customerSignUpService.signUp(signUpForm);
        return ResponseEntity.ok("회원가입 완료");
    }
}
