package ru.geekbrains.cashFlowManager.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class OwnerPersonalDataDTO {
    private String id;
    private String ownerSurname;
    private String ownerName;
    private String ownerPatronymic;
    private Timestamp createdAt;
    private String email;
    private String phoneNumber;
    private String address;
    private Date dateOfBirth;
    private String gender;

    public OwnerPersonalDataDTO() {}

    public OwnerPersonalDataDTO(String id, String ownerSurname, String ownerName, String ownerPatronymic, Timestamp createdAt,
                                String email, String phoneNumber, String address, Date dateOfBirth, String gender) {
        this.id = id;
        this.ownerSurname = ownerSurname;
        this.ownerName = ownerName;
        this.ownerPatronymic = ownerPatronymic;
        this.createdAt = createdAt;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "OwnerPersonalDataDTO{" +
                "id='" + id + '\'' +
                ", ownerSurname='" + ownerSurname + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerPatronymic='" + ownerPatronymic + '\'' +
                ", createdAt=" + createdAt +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                '}';
    }
}