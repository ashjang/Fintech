package com.ashjang.account.domain.model;

import com.ashjang.account.domain.dto.TransactionForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long money;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Account receiver;

    public static Transaction from(TransactionForm form, Account sender, Account receiver) {
        return Transaction.builder()
                .money(form.getMoney())
                .content(form.getContent())
                .createdAt(LocalDateTime.now())
                .sender(sender)
                .receiver(receiver)
                .build();
    }
}
