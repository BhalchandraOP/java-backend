package com.bhalchandra.eventProcessor.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhalchandra.eventProcessor.entity.WTransaction;

public interface TransactionRepository
        extends JpaRepository<WTransaction, Long> {

    Optional<WTransaction> findByTransactionId(UUID transactionId);

    boolean existsByTransactionId(UUID transactionId);
}