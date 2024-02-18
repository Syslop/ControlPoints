package ru.geekbrains.cashFlowManager.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.geekbrains.cashFlowManager.dto.TransactionDTO;
import ru.geekbrains.cashFlowManager.repository.TransactionRepository;
import ru.geekbrains.cashFlowManager.repository.impl.mapper.TransactionRowMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    // language=sql
    private final static String FIND_ALL_TRANSACTIONS_SQL = "SELECT id, account_id, amount, currency, operation_type, created_at, transaction_description FROM TRANSACTIONS";
    // language=sql
    private final static String INSERT_TRANSACTION_SQL = "INSERT INTO TRANSACTIONS (id, account_id, amount, currency, operation_type, created_at, transaction_description) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?)";
    // language=sql
    private final static String UPDATE_TRANSACTION_SQL = "UPDATE TRANSACTIONS SET account_id = ?, amount = ?, currency = ?, operation_type = ?, created_at = ?, transaction_description = ? WHERE id = ?";
    // language=sql
    private final static String FIND_TRANSACTION_BY_ID_SQL = "SELECT id, account_id, amount, currency, operation_type, created_at, transaction_description FROM TRANSACTIONS WHERE id = ?";
    // language=sql
    private final static String DELETE_TRANSACTION_SQL = "DELETE FROM TRANSACTIONS WHERE id = ?";

    // language=sql
    private final static String FIND_ALL_TRANSACTIONS_BY_OWNER_ID_SQL = "SELECT t.id, t.account_id, t.amount, t.currency, t.operation_type, t.created_at, t.transaction_description " +
            "FROM TRANSACTIONS t " +
            "LEFT JOIN ACCOUNTS a ON t.account_id = a.id " +
            "LEFT JOIN ACCOUNT_OWNERS ao ON a.account_owner_id = ao.id " +
            "WHERE ao.id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String add(TransactionDTO transactionDTO) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_TRANSACTION_SQL);
            ps.setString(1, transactionDTO.getId());
            ps.setString(2, transactionDTO.getAccountId());
            ps.setInt(3, transactionDTO.getAmount());
            ps.setString(4, transactionDTO.getCurrency());
            ps.setString(5, transactionDTO.getOperationType());
            ps.setString(6, transactionDTO.getTransactionDescription());
            return ps;
        });
        return transactionDTO.getId();
    }

    @Override
    public Boolean edit(TransactionDTO newTransactionDTO) {
        int rowsAffected = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(UPDATE_TRANSACTION_SQL);
            ps.setString(1, newTransactionDTO.getAccountId());
            ps.setInt(2, newTransactionDTO.getAmount());
            ps.setString(3, newTransactionDTO.getCurrency());
            ps.setString(4, newTransactionDTO.getOperationType());
            ps.setTimestamp(5, newTransactionDTO.getCreatedAt());
            ps.setString(6, newTransactionDTO.getTransactionDescription());
            ps.setString(7, newTransactionDTO.getId());
            return ps;
        });
        return rowsAffected > 0;
    }

    @Override
    public Optional<TransactionDTO> get(String id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                FIND_TRANSACTION_BY_ID_SQL,
                new TransactionRowMapper(),
                id
        ));
    }

    @Override
    public List<TransactionDTO> findAll() {
        return jdbcTemplate.query(
                FIND_ALL_TRANSACTIONS_SQL,
                new TransactionRowMapper());
    }

    @Override
    public String delete(String id) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con
                    .prepareStatement(DELETE_TRANSACTION_SQL);
            ps.setString(1, id);
            return ps;
        });
        return id;
    }

    @Override
    public List<TransactionDTO> findByOwnerId(String ownerId) {
        return jdbcTemplate.query(
                FIND_ALL_TRANSACTIONS_BY_OWNER_ID_SQL,
                new TransactionRowMapper(),
                ownerId
        );
    }
}
