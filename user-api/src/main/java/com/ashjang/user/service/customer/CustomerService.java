package com.ashjang.user.service.customer;

import com.ashjang.domain.common.UserVo;
import com.ashjang.domain.config.JwtAuthenticationProvider;
import com.ashjang.user.domain.model.Customer;
import com.ashjang.user.domain.repository.CustomerRepository;
import com.ashjang.user.exception.CustomException;
import com.ashjang.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    // 회원정보 조회
    public Optional<Customer> customerDetail(String token) {
        if (!jwtAuthenticationProvider.isValidToken(token)) {
            throw new CustomException(ErrorCode.NOT_VALID_TOKEN);
        }

        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        System.out.println(userVo.getNickname());
        return customerRepository.findByNickname(userVo.getNickname());
    }
}
