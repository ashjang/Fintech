package com.ashjang.account.domain.dto;

import com.ashjang.account.domain.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TransactionDto {
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private Long money;
    private String content;
    private LocalDateTime createdAt;

    public static TransactionDto from(Transaction transaction) {
        return new TransactionDto(
                transaction.getSender().getAccountNumber(),
                transaction.getReceiver().getAccountNumber(),
                transaction.getMoney(),
                transaction.getContent(),
                transaction.getCreatedAt()
        );
    }
}
