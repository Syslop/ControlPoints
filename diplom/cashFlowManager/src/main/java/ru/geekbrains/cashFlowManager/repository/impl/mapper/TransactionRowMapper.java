package ru.geekbrains.cashFlowManager.repository.impl.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.geekbrains.cashFlowManager.dto.TransactionDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TransactionRowMapper implements RowMapper<TransactionDTO> {
    @Override
    public TransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(rs.getString("id"));
        transactionDTO.setAccountId(rs.getString("account_id"));
        transactionDTO.setAmount(rs.getInt("amount"));
        transactionDTO.setCurrency(rs.getString("currency"));
        transactionDTO.setOperationType(rs.getString("operation_type"));
        transactionDTO.setCreatedAt(rs.getTimestamp("created_at"));
        transactionDTO.setTransactionDescription(rs.getString("transaction_description"));
        return transactionDTO;
    }
}