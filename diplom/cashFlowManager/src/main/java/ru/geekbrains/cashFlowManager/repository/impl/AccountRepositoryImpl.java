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

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    // language=sql
    private final static String FIND_ALL_ACCOUNTS_SQL = "select id, account_number, account_owner_id, balance, currency, status, created_at from ACCOUNTS WHERE status != 'DELETED' order by created_at desc;";

    // language=sql
    private final static String INSERT_ACCOUNT_SQL = "insert into ACCOUNTS (id, account_number, account_owner_id, balance, currency, status, created_at) values (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP);";

    // language=sql
    private final static String UPDATE_ACCOUNT_SQL = "update ACCOUNTS set account_number = ?, account_owner_id = ?, balance = ?, currency = ?, status = ? where id = ?";

    // language=sql
    private final static String FIND_ACCOUNT_BY_ID_SQL = "select id, account_number, account_owner_id, balance, currency, status, created_at from ACCOUNTS where id = ? AND status != 'DELETED'";

    // language=sql
    private final static String DELETE_ACCOUNT_SQL = "update ACCOUNTS set status = ? where id = ?";

    // language=sql
    private final static String FIND_ALL_ACCOUNT_BY_OWNER_ID_SQL = "select id, account_number, account_owner_id, balance, currency, status, created_at from ACCOUNTS where account_owner_id = ? AND status != 'DELETED' order by created_at desc;";

    // language=sql
    private final static String FIND_ALL_DELETED_ACCOUNT_LIST_SQL = "select id, account_number, account_owner_id, balance, currency, status, created_at from ACCOUNTS where status = ? order by created_at desc;";

    // language=sql
    private final static String FIND_ALL_DELETED_ACCOUNT_BY_OWNER_ID_SQL = "select id, account_number, account_owner_id, balance, currency, status, created_at from ACCOUNTS where status = ? and account_owner_id = ? order by created_at desc;";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String add(AccountDTO accountDTO) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con
                    .prepareStatement(INSERT_ACCOUNT_SQL);
            ps.setString(1, accountDTO.getId());
            ps.setString(2, accountDTO.getAccountNumber());
            ps.setString(3, accountDTO.getAccountOwnerId());
            ps.setInt(4, accountDTO.getBalance());
            ps.setString(5, accountDTO.getCurrency());
            ps.setString(6, accountDTO.getStatus());
            return ps;
        });
        return accountDTO.getId();
    }

    @Override
    public Boolean edit(AccountDTO newAccountDTO) {
        int rowsAffected = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(UPDATE_ACCOUNT_SQL);
            ps.setString(1, newAccountDTO.getAccountNumber());
            ps.setObject(2, newAccountDTO.getAccountOwnerId());
            ps.setInt(3, newAccountDTO.getBalance());
            ps.setString(4, newAccountDTO.getCurrency());
            ps.setString(5, newAccountDTO.getStatus());
            ps.setString(6, newAccountDTO.getId());
            return ps;
        });
        return rowsAffected > 0;
    }

    @Override
    public Optional<AccountDTO> get(String id) {
         return Optional.ofNullable(jdbcTemplate.queryForObject(
                 FIND_ACCOUNT_BY_ID_SQL,
                 new AccountRowMapper(),
                 id
         ));
    }

    @Override
    public List<AccountDTO> findAll() {
        return jdbcTemplate.query(
                FIND_ALL_ACCOUNTS_SQL,
                new AccountRowMapper()
        );
    }

    @Override
    public String delete(String id) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con
                    .prepareStatement(DELETE_ACCOUNT_SQL);
            ps.setString(1, "DELETED");
            ps.setString(2, id);
            return ps;
        });
        return id;
    }

    @Override
    public List<AccountDTO> findByOwnerId(String ownerId) {
        return jdbcTemplate.query(
                FIND_ALL_ACCOUNT_BY_OWNER_ID_SQL,
                new AccountRowMapper(),
                ownerId
        );
    }

    @Override
    public List<AccountDTO> findDeletedAccountList() {
        return jdbcTemplate.query(
                FIND_ALL_DELETED_ACCOUNT_LIST_SQL,
                new AccountRowMapper(),
                "DELETED"
        );
    }

    @Override
    public List<AccountDTO> findDeletedAccountsByOwnerId(String ownerId) {
        return jdbcTemplate.query(
                FIND_ALL_DELETED_ACCOUNT_BY_OWNER_ID_SQL,
                new AccountRowMapper(),
                "DELETED",
                ownerId
        );
    }
}
