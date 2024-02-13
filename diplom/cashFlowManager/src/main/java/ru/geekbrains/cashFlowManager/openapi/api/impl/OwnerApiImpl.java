package ru.geekbrains.cashFlowManager.openapi.api.impl;

import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cashFlowManager.openapi.api.OwnerApi;

@RestController
public class OwnerApiImpl implements OwnerApi {
    //Добавление информации о владельце счета
    @Override
    public ResponseEntity<AddOwnerResponse> addOwner(AddOwnerRequest addOwnerRequest) {
        return null;
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
        return null;
    }

    //Изменение информации о владельце счета
    @Override
    public ResponseEntity<UpdateOwnerResponse> updateOwner(Integer accountId, UpdateOwnerRequest updateOwnerRequest) {
        return null;
    }
}
