package ru.geekbrains.cashFlowManager.openapi.api.impl;

import org.openapitools.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cashFlowManager.dto.AccountDTO;
import ru.geekbrains.cashFlowManager.dto.AccountOwnerDTO;
import ru.geekbrains.cashFlowManager.dto.OwnerPersonalDataDTO;
import ru.geekbrains.cashFlowManager.openapi.api.OwnerApi;
import ru.geekbrains.cashFlowManager.repository.RepositoryBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        List<AccountDTO> accountDTOByOwnerIdList = repositoryBean.getAccountRepository().findByOwnerId(accountId);
        List<AccountDTO> accountDTODeletedByOwnerIdList = repositoryBean.getAccountRepository().findDeletedAccountsByOwnerId(accountId);

        int diff = accountDTOByOwnerIdList.size() - accountDTODeletedByOwnerIdList.size();

        if (diff == 0) {
            DeleteOwnerResponse deleteOwnerResponse = new DeleteOwnerResponse();
            deleteOwnerResponse.setOwnerId(repositoryBean.getAccountOwnerRepository().delete(accountId));
            return ResponseEntity.ok(deleteOwnerResponse);
        } else {
            return null;
        }
    }

    //Получение списка всех удаленных пользователей
    @Override
    public ResponseEntity<DeletedOwnersListResponse> getDeletedUsersList() {
        DeletedOwnersListResponse deletedOwnersListResponse = new DeletedOwnersListResponse();
        List<OwnerPersonalDataItem> ownerPersonalDataItemList = repositoryBean.getAccountOwnerRepository().findDeletedAccountList().stream()
                .map(ownerPersonalDataDTO -> {
                    OwnerPersonalDataItem ownerPersonalDataItem = new OwnerPersonalDataItem();
                    ownerPersonalDataItem.setId(ownerPersonalDataDTO.getId());
                    ownerPersonalDataItem.setOwnerSurname(ownerPersonalDataDTO.getOwnerSurname());
                    ownerPersonalDataItem.setOwnerName(ownerPersonalDataDTO.getOwnerName());
                    ownerPersonalDataItem.setOwnerPatronymic(ownerPersonalDataDTO.getOwnerPatronymic());
                    ownerPersonalDataItem.setAddress(ownerPersonalDataDTO.getAddress());
                    ownerPersonalDataItem.setStatus(ownerPersonalDataDTO.getStatus());
                    ownerPersonalDataItem.setDateOfBirth(ownerPersonalDataDTO.getDateOfBirth().getTime());
                    ownerPersonalDataItem.setEmail(ownerPersonalDataDTO.getEmail());
                    ownerPersonalDataItem.setGender(ownerPersonalDataDTO.getGender());
                    ownerPersonalDataItem.setPhoneNumber(ownerPersonalDataDTO.getPhoneNumber());
                    ownerPersonalDataItem.setCreatedAt(ownerPersonalDataDTO.getCreated().getTime());
                    return ownerPersonalDataItem;
                }).collect(Collectors.toList());
        deletedOwnersListResponse.setDeletedOwnersList(ownerPersonalDataItemList);
        return ResponseEntity.ok(deletedOwnersListResponse);
    }

    //Получение данных пользователя
    @Override
    public ResponseEntity<OwnerPersonalDataInfoResponse> getOwnerInfo(String ownerId) {
        OwnerPersonalDataDTO ownerPersonalDataDTO = repositoryBean.getOwnerPersonalDataRepository().get(ownerId).orElseThrow();
        OwnerPersonalDataItem ownerPersonalDataItem = new OwnerPersonalDataItem();
        BeanUtils.copyProperties(ownerPersonalDataDTO, ownerPersonalDataItem); // Копирование свойств
        ownerPersonalDataItem.setCreatedAt(ownerPersonalDataDTO.getCreated().getTime());
        ownerPersonalDataItem.setDateOfBirth(ownerPersonalDataDTO.getDateOfBirth().getTime());

        OwnerPersonalDataInfoResponse ownerPersonalDataInfoResponse = new OwnerPersonalDataInfoResponse();
        ownerPersonalDataInfoResponse.setAccount(ownerPersonalDataItem);
        return ResponseEntity.ok(ownerPersonalDataInfoResponse);
    }

    //Получение списка всех пользователей
    @Override
    public ResponseEntity<OwnersListResponse> getOwnersList() {
        OwnersListResponse ownersListResponse = new OwnersListResponse();
        List<OwnerPersonalDataItem> ownerPersonalDataItemList = repositoryBean.getOwnerPersonalDataRepository().findAll().stream()
                .map(ownerPersonalDataDTO -> {
                    OwnerPersonalDataItem ownerPersonalDataItem = new OwnerPersonalDataItem();
            ownerPersonalDataItem.setId(ownerPersonalDataDTO.getId());
            ownerPersonalDataItem.setOwnerSurname(ownerPersonalDataDTO.getOwnerSurname());
            ownerPersonalDataItem.setOwnerName(ownerPersonalDataDTO.getOwnerName());
            ownerPersonalDataItem.setOwnerPatronymic(ownerPersonalDataDTO.getOwnerPatronymic());
            ownerPersonalDataItem.setAddress(ownerPersonalDataDTO.getAddress());
            ownerPersonalDataItem.setStatus(ownerPersonalDataDTO.getStatus());
            ownerPersonalDataItem.setDateOfBirth(ownerPersonalDataDTO.getDateOfBirth().getTime());
            ownerPersonalDataItem.setEmail(ownerPersonalDataDTO.getEmail());
            ownerPersonalDataItem.setGender(ownerPersonalDataDTO.getGender());
            ownerPersonalDataItem.setPhoneNumber(ownerPersonalDataDTO.getPhoneNumber());
            ownerPersonalDataItem.setCreatedAt(ownerPersonalDataDTO.getCreated().getTime());
            return ownerPersonalDataItem;
        }).collect(Collectors.toList());
        ownersListResponse.setAccountsList(ownerPersonalDataItemList);
        return ResponseEntity.ok(ownersListResponse);
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

        repositoryBean.getOwnerPersonalDataRepository().edit(ownerPersonalDataDTO);

        UpdateOwnerResponse updateOwnerResponse = new UpdateOwnerResponse(ownerPersonalDataItem);
        return ResponseEntity.ok(updateOwnerResponse);
    }
}
