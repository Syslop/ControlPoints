package ru.geekbrains.cashFlowManager.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountOwnerDTO {
    private String id;
    private int ownerPersonalDataId;
    private String status;
    private Timestamp createdAt;
    private int ownershipPercentage;

    public AccountOwnerDTO() {}

    public AccountOwnerDTO(String id, int ownerPersonalDataId, String status, Timestamp createdAt, int ownershipPercentage) {
        this.id = id;
        this.ownerPersonalDataId = ownerPersonalDataId;
        this.status = status;
        this.createdAt = createdAt;
        this.ownershipPercentage = ownershipPercentage;
    }

    @Override
    public String toString() {
        return "AccountOwnersDTO{" +
                "id='" + id + '\'' +
                ", ownerPersonalDataId=" + ownerPersonalDataId +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", ownershipPercentage=" + ownershipPercentage +
                '}';
    }
}
