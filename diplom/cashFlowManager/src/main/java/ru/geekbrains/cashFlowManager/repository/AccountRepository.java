package ru.geekbrains.cashFlowManager.repository;

import ru.geekbrains.cashFlowManager.dto.AccountDTO;

import java.util.List;

public interface AccountRepository extends BaseRepository<AccountDTO, String> {
    List<AccountDTO> findByOwnerId(String ownerId);

    List<AccountDTO> findDeletedAccountList();

    List<AccountDTO> findDeletedAccountsByOwnerId(String ownerId);
}
