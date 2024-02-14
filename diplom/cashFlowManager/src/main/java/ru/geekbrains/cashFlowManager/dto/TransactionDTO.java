package ru.geekbrains.cashFlowManager.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class TransactionDTO {
    private String id;
    private String accountId;
    private int amount;
    private String currency;
    private String operationType;
    private Timestamp createdAt;
    private String transactionDescription;

    // Constructors
    public TransactionDTO() {}

    public TransactionDTO(String id, String accountId, int amount, String currency, String operationType,
                           Timestamp createdAt, String transactionDescription) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.currency = currency;
        this.operationType = operationType;
        this.createdAt = createdAt;
        this.transactionDescription = transactionDescription;
    }

    @Override
    public String toString() {
        return "TransactionsDTO{" +
                "id='" + id + '\'' +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", operationType='" + operationType + '\'' +
                ", createdAt=" + createdAt +
                ", transactionDescription='" + transactionDescription + '\'' +
                '}';
    }
}
