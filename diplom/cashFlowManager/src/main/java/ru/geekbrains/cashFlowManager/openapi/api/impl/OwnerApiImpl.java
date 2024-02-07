package ru.geekbrains.cashFlowManager.openapi.api.impl;

import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cashFlowManager.openapi.api.OwnerApi;

@RestController
public class OwnerApiImpl implements OwnerApi {
    @Override
    public ResponseEntity<AddOwnerResponse> ownerAddAccountIdPost(Integer accountId, AddOwnerRequest addOwnerRequest) {
        return null;
    }

    @Override
    public ResponseEntity<DeleteOwnerResponse> ownerDeleteAccountIdPost(Integer accountId) {
        return null;
    }

    @Override
    public ResponseEntity<DeletedUsersListResponse> ownerDeletedListGet() {
        return null;
    }

    @Override
    public ResponseEntity<UpdateOwnerResponse> ownerUpdateAccountIdPost(Integer accountId, UpdateOwnerRequest updateOwnerRequest) {
        return null;
    }
}
