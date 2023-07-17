package com.ashjang.account.domain.repository;

import com.ashjang.account.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByReceiver_Id(Long receiverId);

    List<Transaction> findAllBySender_Id(Long senderId);
}
