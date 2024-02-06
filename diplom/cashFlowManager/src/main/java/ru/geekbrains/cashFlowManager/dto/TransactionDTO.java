package ru.geekbrains.cashFlowManager.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TransactionDTO {
    private String id;
    private int accountId;
    private int amount;
    private String currency;
    private String operationType;
    private Timestamp createdAt;
    private String transactionType;
    private String transactionDescription;

    // Constructors
    public TransactionDTO() {}

    public TransactionDTO(String id, int accountId, int amount, String currency, String operationType,
                           Timestamp createdAt, String transactionType, String transactionDescription) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.currency = currency;
        this.operationType = operationType;
        this.createdAt = createdAt;
        this.transactionType = transactionType;
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
                ", transactionType='" + transactionType + '\'' +
                ", transactionDescription='" + transactionDescription + '\'' +
                '}';
    }
}
