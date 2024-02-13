package ru.geekbrains.cashFlowManager.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.geekbrains.cashFlowManager.dto.AccountDTO;
import ru.geekbrains.cashFlowManager.repository.AccountRepository;
import ru.geekbrains.cashFlowManager.repository.impl.mapper.AccountRowMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    // language=sql
    private final static String FIND_ALL_ACCOUNTS_SQL = "select id, account_number, account_owner_id, balance, currency, status, created_at from ACCOUNTS order by created_at desc;";

    // language=sql
    private final static String INSERT_ACCOUNT_SQL = "insert into ACCOUNTS (id, account_number, account_owner_id, balance, currency, status, created_at) values (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP);";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID add(AccountDTO accountDTO) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con
                    .prepareStatement(INSERT_ACCOUNT_SQL);
            ps.setObject(1, accountDTO.getId());
            ps.setString(2, accountDTO.getAccountNumber());
            ps.setObject(3, accountDTO.getAccountOwnerId());
            ps.setInt(2, accountDTO.getBalance());
            ps.setString(2, accountDTO.getCurrency());
            ps.setString(2, accountDTO.getStatus());
            return ps;
        });
        return accountDTO.getId();
    }

    @Override
    public Boolean edit(AccountDTO newAccountDTO) {
        return null;
    }

    @Override
    public Optional<AccountDTO> get(String id) {
        return Optional.empty();
    }

    @Override
    public List<AccountDTO> findAll() {
        return jdbcTemplate.query(
                FIND_ALL_ACCOUNTS_SQL,
                new AccountRowMapper()
        );
    }

    @Override
    public UUID delete(String id) {
        return null;
    }
}
