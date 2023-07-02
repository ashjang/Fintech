package com.ashjang.account;

import com.ashjang.account.domain.dto.AccountType;
import com.ashjang.account.domain.dto.BankType;
import com.ashjang.user.domain.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.ashjang.user.domain.repository", "com.ashjang.account"})
@EntityScan(basePackages = {"com.ashjang.account", "com.ashjang.user.domain.model"})
@ServletComponentScan
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
