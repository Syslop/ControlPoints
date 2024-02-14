package ru.geekbrains.cashFlowManager.openapi.api.impl;

import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cashFlowManager.dto.AccountOwnerDTO;
import ru.geekbrains.cashFlowManager.dto.OwnerPersonalDataDTO;
import ru.geekbrains.cashFlowManager.openapi.api.OwnerApi;
import ru.geekbrains.cashFlowManager.repository.RepositoryBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@RestController
public class OwnerApiImpl implements OwnerApi {
    private final RepositoryBean repositoryBean;

    @Autowired
    public OwnerApiImpl(RepositoryBean repositoryBean) {
        this.repositoryBean = repositoryBean;
    }

    //Добавление информации о владельце счета
    @Override
    public ResponseEntity<AddOwnerResponse> addOwner(AddOwnerRequest addOwnerRequest) {
        OwnerPersonalDataItem ownerPersonalDataItem = addOwnerRequest.getOwner();

        OwnerPersonalDataDTO ownerPersonalDataDTO = new OwnerPersonalDataDTO();
        ownerPersonalDataDTO.setOwnerName(ownerPersonalDataItem.getOwnerName());
        ownerPersonalDataDTO.setOwnerSurname(ownerPersonalDataItem.getOwnerSurname());
        ownerPersonalDataDTO.setOwnerPatronymic(ownerPersonalDataItem.getOwnerPatronymic());
        ownerPersonalDataDTO.setAddress(ownerPersonalDataItem.getAddress());
        ownerPersonalDataDTO.setEmail(ownerPersonalDataItem.getEmail());
        ownerPersonalDataDTO.setGender(ownerPersonalDataItem.getGender());
        ownerPersonalDataDTO.setId(ownerPersonalDataItem.getId());
        ownerPersonalDataDTO.setDateOfBirth(Timestamp.from(Instant.ofEpochSecond(ownerPersonalDataItem.getDateOfBirth())));
        ownerPersonalDataDTO.setPhoneNumber(ownerPersonalDataItem.getPhoneNumber());

        AccountOwnerDTO accountOwnerDTO = new AccountOwnerDTO();
        accountOwnerDTO.setId(String.valueOf(UUID.randomUUID()));
        accountOwnerDTO.setCreatedAt(Timestamp.from(Instant.ofEpochSecond(ownerPersonalDataItem.getCreatedAt())));
        accountOwnerDTO.setStatus("CREATED");
        accountOwnerDTO.setOwnerPersonalDataId(repositoryBean.getOwnerPersonalDataRepository().add(ownerPersonalDataDTO));

        AddOwnerResponse addOwnerResponse = new AddOwnerResponse(repositoryBean.getAccountOwnerRepository().add(accountOwnerDTO));
        return ResponseEntity.ok(addOwnerResponse);
    }

    //Удаление владельца счета
    @Override
    public ResponseEntity<DeleteOwnerResponse> deleteOwner(String accountId) {
        return null;
    }

    //Получение списка всех удаленных пользователей
    @Override
    public ResponseEntity<DeletedUsersListResponse> getDeletedUsersList() {
        return null;
    }

    //Получение данных пользователя
    @Override
    public ResponseEntity<OwnerPersonalDataInfoResponse> getOwnerInfo(String ownerId) {
        return null;
    }

    //Получение списка всех пользователей
    @Override
    public ResponseEntity<OwnersListResponse> getOwnersList() {
        /*OwnersListResponse ownersListResponse = new OwnersListResponse();
        List<OwnerPersonalDataItem> ownerPersonalDataItemList = new ArrayList<>();
        List<AccountOwnerDTO> accountOwnerDTOList = repositoryBean.getAccountOwnerRepository().findAll();
        for (int i = 0; i < accountOwnerDTOList.size(); i++) {
            AccountOwnerDTO accountOwnerDTO = accountOwnerDTOList.get(i);

        }


        return ResponseEntity.ok(accountsListResponse);
        */
        return null;
    }

    //Изменение информации о владельце счета
    @Override
    public ResponseEntity<UpdateOwnerResponse> updateOwner(String ownerId, UpdateOwnerRequest updateOwnerRequest) {
        OwnerPersonalDataItem ownerPersonalDataItem = updateOwnerRequest.getOwner();

        AccountOwnerDTO accountOwnerDTO = repositoryBean.getAccountOwnerRepository().get(ownerId).orElseThrow();
        OwnerPersonalDataDTO ownerPersonalDataDTO = repositoryBean.getOwnerPersonalDataRepository().get(accountOwnerDTO.getOwnerPersonalDataId()).orElseThrow();

        ownerPersonalDataDTO.setOwnerName(ownerPersonalDataItem.getOwnerName());
        ownerPersonalDataDTO.setOwnerSurname(ownerPersonalDataItem.getOwnerSurname());
        ownerPersonalDataDTO.setOwnerPatronymic(ownerPersonalDataItem.getOwnerPatronymic());
        ownerPersonalDataDTO.setAddress(ownerPersonalDataItem.getAddress());
        ownerPersonalDataDTO.setEmail(ownerPersonalDataItem.getEmail());
        ownerPersonalDataDTO.setGender(ownerPersonalDataItem.getGender());
        ownerPersonalDataDTO.setId(ownerPersonalDataItem.getId());
        ownerPersonalDataDTO.setDateOfBirth(Timestamp.from(Instant.ofEpochSecond(ownerPersonalDataItem.getDateOfBirth())));
        ownerPersonalDataDTO.setPhoneNumber(ownerPersonalDataItem.getPhoneNumber());

        accountOwnerDTO.setId(String.valueOf(UUID.randomUUID()));
        accountOwnerDTO.setCreatedAt(Timestamp.from(Instant.ofEpochSecond(ownerPersonalDataItem.getCreatedAt())));
        accountOwnerDTO.setStatus("UPDATED");

        repositoryBean.getOwnerPersonalDataRepository().edit(ownerPersonalDataDTO);
        repositoryBean.getAccountOwnerRepository().edit(accountOwnerDTO);

        UpdateOwnerResponse updateOwnerResponse = new UpdateOwnerResponse(ownerPersonalDataItem);
        return ResponseEntity.ok(updateOwnerResponse);
    }
}
