package ru.geekbrains.cashFlowManager.openapi.api.impl;

import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cashFlowManager.dto.AccountDTO;
import ru.geekbrains.cashFlowManager.openapi.api.AccountApi;
import ru.geekbrains.cashFlowManager.repository.RepositoryBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountApiImpl implements AccountApi {
    private final RepositoryBean repositoryBean;

    @Autowired
    public AccountApiImpl(RepositoryBean repositoryBean) {
        this.repositoryBean = repositoryBean;
    }

    //Создание нового счета
    @Override
    public ResponseEntity<AddAccountResponse> addAccount(AddAccountRequest addAccountRequest) {
        AccountItem accountItem = addAccountRequest.getAccount();

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(accountItem.getId());
        accountDTO.setAccountNumber(accountItem.getAccountNumber());
        accountDTO.setAccountOwnerId(accountItem.getAccountOwnerId());
        accountDTO.setBalance(accountItem.getBalance());
        accountDTO.setCurrency(accountItem.getCurrency());
        accountDTO.setStatus(accountItem.getStatus());
        accountDTO.setCreatedAt(Timestamp.from(Instant.ofEpochSecond(accountItem.getCreatedAt())));

        AddAccountResponse addAccountResponse = new AddAccountResponse();
        addAccountResponse.setAccountId(String.valueOf(repositoryBean.getAccountRepository().add(accountDTO)));
        return ResponseEntity.ok(addAccountResponse);
    }

    //Удаление счета
    @Override
    public ResponseEntity<DeleteAccountResponse> deleteAccount(String accountId) {
        return null;
    }

    //Получение всех счетов пользователя
    @Override
    public ResponseEntity<AccountByOwnerListResponse> getAccountByOwnerList(String ownerId) {
        return null;
    }

    //Получение информации о конкретном счете
    @Override
    public ResponseEntity<AccountInfoResponse> getAccountInfo(String accountId) {
        return null;
    }

    //Получение списка всех счетов
    @Override
    public ResponseEntity<AccountsListResponse> getAccountsList() {
        AccountsListResponse accountsListResponse = new AccountsListResponse();
        List<AccountItem> accountItemList = repositoryBean.getAccountRepository().findAll().stream().map(accountDTO -> {
            AccountItem accountItem = new AccountItem();
            accountItem.setId(accountDTO.getId());
            accountItem.setAccountNumber(accountDTO.getAccountNumber());
            accountItem.setAccountOwnerId(accountDTO.getAccountOwnerId());
            accountItem.setBalance(accountDTO.getBalance());
            accountItem.setCurrency(accountDTO.getCurrency());
            accountItem.setStatus(accountDTO.getStatus());
            accountItem.setCreatedAt(accountDTO.getCreatedAt().getTime());
            return accountItem;
        }).collect(Collectors.toList());
        accountsListResponse.setAccountsList(accountItemList);
        return ResponseEntity.ok(accountsListResponse);
    }

    //Получение списка всех удаленных счетов
    @Override
    public ResponseEntity<DeletedAccountsListResponse> getDeletedAccountsList() {
        return null;
    }

    //Обновление информации о счете
    @Override
    public ResponseEntity<UpdateAccountResponse> updateAccount(String accountId, UpdateAccountRequest updateAccountRequest) {
        AccountItem accountItem = updateAccountRequest.getAccount();

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(accountItem.getId());
        accountDTO.setAccountNumber(accountItem.getAccountNumber());
        accountDTO.setAccountOwnerId(accountItem.getAccountOwnerId());
        accountDTO.setBalance(accountItem.getBalance());
        accountDTO.setCurrency(accountItem.getCurrency());
        accountDTO.setStatus(accountItem.getStatus());
        accountDTO.setCreatedAt(Timestamp.from(Instant.ofEpochSecond(accountItem.getCreatedAt())));

        repositoryBean.getAccountRepository().edit(accountDTO);

        UpdateAccountResponse updateAccountResponse = new UpdateAccountResponse();
        updateAccountResponse.setAccount(accountItem);

        return ResponseEntity.ok(updateAccountResponse);
    }
}
