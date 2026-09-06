package com.bhalchandra.eventProcessor.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.bhalchandra.eventProcessor.entity.WTransaction.TransactionType;

public class TransactionRequest {

    private UUID transactionId;

    private UUID userId;

    private BigDecimal amount;

    private TransactionType type;

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}