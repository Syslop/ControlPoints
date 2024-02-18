package ru.geekbrains.cashFlowManager.repository;

import ru.geekbrains.cashFlowManager.dto.AccountDTO;
import ru.geekbrains.cashFlowManager.dto.TransactionDTO;

import java.util.List;

public interface TransactionRepository extends BaseRepository<TransactionDTO, String> {
    List<TransactionDTO> findByOwnerId(String ownerId);
}
