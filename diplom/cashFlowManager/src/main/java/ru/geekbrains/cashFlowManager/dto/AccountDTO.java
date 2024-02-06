package ru.geekbrains.cashFlowManager.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountDTO {
    private String id;
    private String accountNumber;
    private int accountOwnerId;
    private int balance;
    private String currency;
    private String status;
    private Timestamp createdAt;

    public AccountDTO() {}

    public AccountDTO(String id, String accountNumber, int accountOwnerId, int balance, String currency, String status, Timestamp createdAt) {
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

