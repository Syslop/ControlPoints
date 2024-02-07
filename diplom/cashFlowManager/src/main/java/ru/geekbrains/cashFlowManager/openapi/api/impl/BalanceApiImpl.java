package ru.geekbrains.cashFlowManager.openapi.api.impl;

import org.openapitools.model.ChangeBalanceRequest;
import org.openapitools.model.ChangeBalanceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cashFlowManager.openapi.api.BalanceApi;

@RestController
public class BalanceApiImpl implements BalanceApi {
    @Override
    public ResponseEntity<ChangeBalanceResponse> balanceUpdateAccountIdPost(Integer accountId, ChangeBalanceRequest changeBalanceRequest) {
        return null;
    }
}
