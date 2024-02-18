package ru.geekbrains.cashFlowManager.repository;

import ru.geekbrains.cashFlowManager.dto.AccountDTO;
import ru.geekbrains.cashFlowManager.dto.AccountOwnerDTO;
import ru.geekbrains.cashFlowManager.dto.OwnerPersonalDataDTO;

import java.util.List;

public interface AccountOwnerRepository extends BaseRepository<AccountOwnerDTO, String>{
    List<OwnerPersonalDataDTO> findDeletedAccountList();
}