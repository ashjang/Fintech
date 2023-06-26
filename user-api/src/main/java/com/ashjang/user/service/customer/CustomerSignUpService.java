package com.ashjang.user.service.customer;

import com.ashjang.user.domain.SignUpForm;
import com.ashjang.user.domain.dto.CustomerDto;
import com.ashjang.user.domain.model.Customer;
import com.ashjang.user.domain.repository.CustomerRepository;
import com.ashjang.user.exception.CustomException;
import com.ashjang.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerSignUpService {
    private final CustomerRepository customerRepository;

    // 회원가입
    public CustomerDto signUp(SignUpForm signUpForm) {
        // 닉네임 중복일치 여부
        if (isNickNameExists(signUpForm.getNickname())) {
            throw new CustomException(ErrorCode.ALREADY_EXISTS_NICKNAME);
        }
        // 주민등록번호 중복일치 여부
        if (isIdNumberExists(signUpForm.getIdNumber())) {
            throw new CustomException(ErrorCode.ALREADY_EXISTS_REGISTER);
        }

        Customer customer = customerRepository.save(Customer.from(signUpForm));
        return CustomerDto.from(customer);
    }

    // 닉네임 존재여부 확인
    public boolean isNickNameExists(String nickName) {
        return customerRepository.findByNickname(nickName).isPresent();
    }

    // 주민등록번호 존재여부 확인
    public boolean isIdNumberExists(String id_number) {
        return customerRepository.findByIdNumber(id_number).isPresent();
    }
}
