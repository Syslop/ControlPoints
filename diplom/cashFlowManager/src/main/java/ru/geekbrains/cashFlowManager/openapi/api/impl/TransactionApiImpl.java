package ru.geekbrains.cashFlowManager.openapi.api.impl;

import org.openapitools.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cashFlowManager.dto.AccountDTO;
import ru.geekbrains.cashFlowManager.dto.OwnerPersonalDataDTO;
import ru.geekbrains.cashFlowManager.dto.TransactionDTO;
import ru.geekbrains.cashFlowManager.openapi.api.TransactionApi;
import ru.geekbrains.cashFlowManager.repository.RepositoryBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransactionApiImpl implements TransactionApi {
    private final RepositoryBean repositoryBean;

    @Autowired
    public TransactionApiImpl(RepositoryBean repositoryBean) {
        this.repositoryBean = repositoryBean;
    }

    //Изменение средств на счете
    @Override
    public ResponseEntity<ChangeBalanceResponse> changeBalance(String accountId, ChangeBalanceRequest changeBalanceRequest) {
        TransactionItem transactionItem = changeBalanceRequest.getTransaction();
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transactionItem.getId());
        transactionDTO.setCreatedAt(Timestamp.from(Instant.ofEpochSecond(transactionItem.getCreatedAt())));
        transactionDTO.setAccountId(transactionItem.getAccountId());
        transactionDTO.setCurrency(transactionItem.getCurrency());
        transactionDTO.setTransactionDescription(transactionItem.getTransactionDescription());
        transactionDTO.setAmount(transactionItem.getAmount());
        transactionDTO.setOperationType(transactionItem.getOperationType());

        repositoryBean.getTransactionRepository().add(transactionDTO);
        System.out.println(accountId);
        AccountDTO accountDTO = repositoryBean.getAccountRepository().get(accountId).orElseThrow();

        if (transactionDTO.getOperationType().equals("ADD")) {
            accountDTO.setBalance(accountDTO.getBalance() + transactionDTO.getAmount());
        } else {
            accountDTO.setBalance(accountDTO.getBalance() - transactionDTO.getAmount());
        }
        repositoryBean.getAccountRepository().edit(accountDTO);

        ChangeBalanceResponse changeBalanceResponse = new ChangeBalanceResponse();
        changeBalanceResponse.setTransactionId(transactionDTO.getId());
        return ResponseEntity.ok(changeBalanceResponse);
    }

    //Получение всех операций пользователя
    @Override
    public ResponseEntity<TransactionByOwnerListResponse> getTransactionByOwnerList(String ownerId) {
        TransactionByOwnerListResponse transactionByOwnerListResponse = new TransactionByOwnerListResponse();
        List<TransactionItem> transactionItemList = repositoryBean.getTransactionRepository().findByOwnerId(ownerId).stream()
                .map(transactionDTO -> {
                    TransactionItem transactionItem = new TransactionItem();
                    transactionItem.setId(transactionDTO.getId());
                    transactionItem.setAccountId(transactionDTO.getAccountId());
                    transactionItem.setTransactionDescription(transactionDTO.getTransactionDescription());
                    transactionItem.setCurrency(transactionDTO.getCurrency());
                    transactionItem.setAmount(transactionDTO.getAmount());
                    transactionItem.setOperationType(transactionDTO.getOperationType());
                    transactionItem.setCreatedAt(transactionDTO.getCreatedAt().getTime());
                    return transactionItem;
                }).collect(Collectors.toList());
        transactionByOwnerListResponse.setAccountsList(transactionItemList);

        return ResponseEntity.ok(transactionByOwnerListResponse);
    }

    //Получение списка всех операций
    @Override
    public ResponseEntity<TransactionListResponse> getTransactionList() {
        TransactionListResponse transactionListResponse = new TransactionListResponse();
        List<TransactionItem> transactionItemList = repositoryBean.getTransactionRepository().findAll().stream()
                .map(transactionDTO -> {
                    TransactionItem transactionItem = new TransactionItem();
                    transactionItem.setId(transactionDTO.getId());
                    transactionItem.setAccountId(transactionDTO.getAccountId());
                    transactionItem.setTransactionDescription(transactionDTO.getTransactionDescription());
                    transactionItem.setCurrency(transactionDTO.getCurrency());
                    transactionItem.setAmount(transactionDTO.getAmount());
                    transactionItem.setOperationType(transactionDTO.getOperationType());
                    transactionItem.setCreatedAt(transactionDTO.getCreatedAt().getTime());
                    return transactionItem;
                }).collect(Collectors.toList());
        transactionListResponse.setAccountsList(transactionItemList);

        return ResponseEntity.ok(transactionListResponse);
    }
}
