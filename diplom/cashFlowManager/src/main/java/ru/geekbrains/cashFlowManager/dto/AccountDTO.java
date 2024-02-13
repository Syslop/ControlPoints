package ru.geekbrains.cashFlowManager.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class AccountDTO {
    private UUID id;
    private String accountNumber;
    private UUID accountOwnerId;
    private int balance;
    private String currency;
    private String status;
    private Timestamp createdAt;

    public AccountDTO() {}

    public AccountDTO(UUID id, String accountNumber, UUID accountOwnerId, int balance, String currency, String status, Timestamp createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountOwnerId = accountOwnerId;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "AccountsDTO{" +
                "id='" + id + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountOwnerId=" + accountOwnerId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

