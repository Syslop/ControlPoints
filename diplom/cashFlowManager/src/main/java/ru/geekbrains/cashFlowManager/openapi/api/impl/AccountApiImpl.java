package ru.geekbrains.cashFlowManager.openapi.api.impl;

import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cashFlowManager.openapi.api.AccountApi;

@RestController
public class AccountApiImpl implements AccountApi {
    @Override
    public ResponseEntity<AccountInfoResponse> accountAccountIdGet(Integer accountId) {
        return null;
    }

    @Override
    public ResponseEntity<AddAccountResponse> accountAddPost(AddAccountRequest addAccountRequest) {
        return null;
    }

    @Override
    public ResponseEntity<DeleteAccountResponse> accountDeleteAccountIdPost(Integer accountId) {
        return null;
    }

    @Override
    public ResponseEntity<DeletedAccountsListResponse> accountDeletedListGet() {
        return null;
    }

    @Override
    public ResponseEntity<AccountsListResponse> accountListGet() {
        return null;
    }

    @Override
    public ResponseEntity<UpdateAccountResponse> accountUpdateAccountIdPost(Integer accountId, UpdateAccountRequest updateAccountRequest) {
        return null;
    }
}
