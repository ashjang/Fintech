package com.ashjang.user.domain.model;

import com.ashjang.user.domain.SignUpForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AuditOverride(forClass = BaseEntity.class)
public class Customer extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickname;

    private String password;
    private String phone;
    @Column(length = 20)
    private String name;

    @Column(length = 14, unique = true)
    private String idNumber;

    public static Customer from(SignUpForm signUpForm) {
        return Customer.builder()
                .nickname(signUpForm.getNickname())
                .password(signUpForm.getPassword())
                .phone(signUpForm.getPhone())
                .name(signUpForm.getName())
                .idNumber(signUpForm.getId_number())
                .build();
    }
}
