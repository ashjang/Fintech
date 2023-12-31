package com.ashjang.user.domain.repository;

import com.ashjang.user.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByNickname(String nickname);
    Optional<Customer> findByIdNumber(String id_number);
    Optional<Customer> findByIdAndNickname(Long id, String nickname);
}
