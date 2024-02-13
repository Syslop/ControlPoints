package ru.geekbrains.cashFlowManager.repository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class RepositoryBean {
    private final AccountRepository accountRepository;

    @Autowired
    public RepositoryBean(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
