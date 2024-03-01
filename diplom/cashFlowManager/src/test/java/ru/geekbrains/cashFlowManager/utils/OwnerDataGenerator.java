package ru.geekbrains.cashFlowManager.utils;

import org.openapitools.model.AddOwnerRequest;
import org.openapitools.model.OwnerPersonalDataItem;
import ru.geekbrains.cashFlowManager.dto.OwnerPersonalDataDTO;

import java.sql.Timestamp;
import java.time.Instant;

public class OwnerDataGenerator {
    public static OwnerPersonalDataDTO generateOwnerPersonalDataDTO(String id){
        OwnerPersonalDataDTO ownerPersonalDataDTO = new OwnerPersonalDataDTO();

        ownerPersonalDataDTO.setId(id);
        ownerPersonalDataDTO.setOwnerSurname("Иванов");
        ownerPersonalDataDTO.setOwnerName("Василий");
        ownerPersonalDataDTO.setOwnerPatronymic("Саймонович");
        ownerPersonalDataDTO.setEmail("trov@gmail.com");
        ownerPersonalDataDTO.setPhoneNumber("89414752538");
        ownerPersonalDataDTO.setAddress("Тула");
        ownerPersonalDataDTO.setDateOfBirth(Timestamp.from(Instant.ofEpochSecond(791644800000L)));
        ownerPersonalDataDTO.setGender("Мужской");

        return ownerPersonalDataDTO;
    }

    public static AddOwnerRequest generateAddOwnerRequest(String id){
        OwnerPersonalDataItem ownerPersonalDataItem = new OwnerPersonalDataItem();

        ownerPersonalDataItem.setId(id);
        ownerPersonalDataItem.setOwnerSurname("Петров");
        ownerPersonalDataItem.setOwnerName("Алан");
        ownerPersonalDataItem.setOwnerPatronymic("Симонович");
        ownerPersonalDataItem.setEmail("petrov@gmail.com");
        ownerPersonalDataItem.setPhoneNumber("89514752538");
        ownerPersonalDataItem.setAddress("Москва");
        ownerPersonalDataItem.setDateOfBirth(791644800000L);
        ownerPersonalDataItem.setGender("Мужской");
        ownerPersonalDataItem.setStatus("CREATED");
        ownerPersonalDataItem.setCreatedAt(0L);

        AddOwnerRequest addOwnerRequest = new AddOwnerRequest();
        addOwnerRequest.setOwner(ownerPersonalDataItem);
        return addOwnerRequest;
    }
}
