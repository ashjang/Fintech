package com.ashjang.user.service.customer;

import com.ashjang.domain.config.JwtAuthenticationProvider;
import com.ashjang.user.domain.SignInForm;
import com.ashjang.user.domain.dto.CustomerDto;
import com.ashjang.user.domain.model.Customer;
import com.ashjang.user.domain.repository.CustomerRepository;
import com.ashjang.user.exception.CustomException;
import com.ashjang.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerSignInService {
    private final CustomerRepository customerRepository;
    private final JwtAuthenticationProvider jwtProvider;

    // sign-in
    public String customerSignIn(SignInForm signInForm) {
        Customer customer = customerRepository.findByNickname(signInForm.getNickname()).stream()
                .filter(c -> c.getPassword().equals(signInForm.getPassword())).findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.SIGNIN_FAIL));

        return jwtProvider.createToken(customer.getNickname(), customer.getId());
    }
}
