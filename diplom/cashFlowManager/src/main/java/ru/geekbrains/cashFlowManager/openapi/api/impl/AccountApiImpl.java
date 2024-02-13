package ru.geekbrains.cashFlowManager.openapi.api.impl;

import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cashFlowManager.openapi.api.AccountApi;

@RestController
public class AccountApiImpl implements AccountApi {
    //Создание нового счета
    @Override
    public ResponseEntity<AddAccountResponse> addAccount(AddAccountRequest addAccountRequest) {
        return null;
    }

    //Удаление счета
    @Override
    public ResponseEntity<DeleteAccountResponse> deleteAccount(Integer accountId) {
        return null;
    }

    //Получение всех счетов пользователя
    @Override
    public ResponseEntity<AccountByOwnerListResponse> getAccountByOwnerList(String ownerId) {
        return null;
    }

    //Получение информации о конкретном счете
    @Override
    public ResponseEntity<AccountInfoResponse> getAccountInfo(Integer accountId) {
        return null;
    }

    //Получение списка всех счетов
    @Override
    public ResponseEntity<AccountsListResponse> getAccountsList() {
        return null;
    }

    //Получение списка всех удаленных счетов
    @Override
    public ResponseEntity<DeletedAccountsListResponse> getDeletedAccountsList() {
        return null;
    }

    //Обновление информации о счете
    @Override
    public ResponseEntity<UpdateAccountResponse> updateAccount(Integer accountId, UpdateAccountRequest updateAccountRequest) {
        return null;
    }
}
