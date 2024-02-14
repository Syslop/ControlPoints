package ru.geekbrains.cashFlowManager.repository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class RepositoryBean {
    private final AccountRepository accountRepository;

    private final AccountOwnerRepository accountOwnerRepository;

    private final OwnerPersonalDataRepository ownerPersonalDataRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    public RepositoryBean(AccountRepository accountRepository, AccountOwnerRepository accountOwnerRepository, OwnerPersonalDataRepository ownerPersonalDataRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.accountOwnerRepository = accountOwnerRepository;
        this.ownerPersonalDataRepository = ownerPersonalDataRepository;
        this.transactionRepository = transactionRepository;
    }
}
