package com.bhalchandra.eventProcessor.service;

import java.math.BigDecimal;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bhalchandra.eventProcessor.dto.TransactionRequest;
import com.bhalchandra.eventProcessor.entity.WEntity;
import com.bhalchandra.eventProcessor.entity.WTransaction;
import com.bhalchandra.eventProcessor.entity.WTransaction.TransactionType;
import com.bhalchandra.eventProcessor.exception.dupTransactionException;
import com.bhalchandra.eventProcessor.exception.insuffFunds;
import com.bhalchandra.eventProcessor.exception.walletnotfound;
import com.bhalchandra.eventProcessor.repository.TransactionRepository;
import com.bhalchandra.eventProcessor.repository.WRepository;

import jakarta.transaction.Transactional;

@Service
public class WService {

    private final TransactionRepository transactionRepository2;
    private final WRepository walletRepository;

    public WService(
            TransactionRepository transactionRepository,
            WRepository walletRepository) {

        this.transactionRepository2 = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public WTransaction process(TransactionRequest request) {

        // 1. Check if transaction was already processed

        if (transactionRepository2.existsByTransactionId(
                request.getTransactionId())) {

            throw new dupTransactionException(
                    "Transaction already processed");
        }

        // 2. Check transaction type

        if (request.getType() != TransactionType.DEBIT) {

            throw new IllegalArgumentException(
                    "Only DEBIT transactions are supported");
        }

        // 3. Get wallet with database-level lock

        WEntity wallet = walletRepository
                .findByUserId(request.getUserId())
                .orElseThrow(() ->
                        new walletnotfound(
                                "Wallet not found"));

        // 4. Check balance

        BigDecimal currentBalance = wallet.getBalance();

        if (currentBalance.compareTo(request.getAmount()) < 0) {

            throw new insuffFunds("Insufficient funds");
        }

        BigDecimal newBalance =
                currentBalance.subtract(request.getAmount());

        wallet.setBalance(newBalance);

        walletRepository.save(wallet);

        // 6. Create transaction

        WTransaction transaction =
                new WTransaction(
                        request.getTransactionId(),
                        request.getUserId(),
                        request.getAmount(),
                        request.getType());

        // 7. Save transaction

        try {

            transactionRepository2.saveAndFlush(
                    transaction);

        } catch (DataIntegrityViolationException e) {

            throw new dupTransactionException(
                    "Transaction already processed");
        }

        // 8. Return transaction

        return transaction;
    }
}