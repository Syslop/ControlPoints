package ru.geekbrains.cashFlowManager.openapi.api.impl;

import org.openapitools.model.ChangeBalanceRequest;
import org.openapitools.model.ChangeBalanceResponse;
import org.openapitools.model.TransactionByOwnerListResponse;
import org.openapitools.model.TransactionListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cashFlowManager.openapi.api.TransactionApi;

@RestController
public class TransactionApiImpl implements TransactionApi {
    //Изменение средств на счете
    @Override
    public ResponseEntity<ChangeBalanceResponse> changeBalance(Integer accountId, ChangeBalanceRequest changeBalanceRequest) {
        return null;
    }

    //Получение всех операций пользователя
    @Override
    public ResponseEntity<TransactionByOwnerListResponse> getTransactionByOwnerList(String ownerId) {
        return null;
    }

    //Получение списка всех операций
    @Override
    public ResponseEntity<TransactionListResponse> getTransactionList() {
        return null;
    }
}
