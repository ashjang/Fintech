package com.ashjang.user.controller;

import com.ashjang.user.domain.dto.CustomerDto;
import com.ashjang.user.domain.model.Customer;
import com.ashjang.user.exception.CustomException;
import com.ashjang.user.exception.ErrorCode;
import com.ashjang.user.service.customer.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @ApiOperation(value = "고객용 회원정보 조회", response = CustomerDto.class)
    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getUserDetail(@RequestHeader(value = "X-AUTH-TOKEN") String token) {
        Customer customer = customerService.customerDetail(token)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        return ResponseEntity.ok(CustomerDto.from(customer));
    }
}
