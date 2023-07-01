package com.ashjang.user.controller;

import com.ashjang.user.domain.dto.ChangeDetailForm;
import com.ashjang.user.domain.dto.CustomerDto;
import com.ashjang.user.domain.model.Customer;
import com.ashjang.user.exception.CustomException;
import com.ashjang.user.exception.ErrorCode;
import com.ashjang.user.service.customer.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @ApiOperation(value = "고객용 회원정보 조회", response = CustomerDto.class)
    @PostMapping("/getInfo")
    public ResponseEntity<CustomerDto> getUserDetail(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                                     @RequestParam String password) {
        Customer customer = customerService.customerDetail(token, password);
        return ResponseEntity.ok(CustomerDto.from(customer));
    }

    @ApiOperation(value = "고객용 회원정보 수정", response = CustomerDto.class)
    @PutMapping("/changeInfo")
    public ResponseEntity<CustomerDto> setUserDetail(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                                     @RequestBody ChangeDetailForm form) {
        Customer customer = customerService.changeDetail(token, form);
        return ResponseEntity.ok(CustomerDto.from(customer));
    }
}
