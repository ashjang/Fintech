package com.ashjang.user.controller;

import com.ashjang.user.domain.SignInForm;
import com.ashjang.user.service.customer.CustomerSignInService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signin")
@RequiredArgsConstructor
public class SignInController {
    private final CustomerSignInService customerSignInService;

    @ApiOperation(value = "고객용 로그인", response = String.class)
    @PostMapping("/customer")
    public ResponseEntity<?> customerSignIn(@RequestBody SignInForm signInForm) {
        return ResponseEntity.ok(customerSignInService.customerSignIn(signInForm));
    }
}
