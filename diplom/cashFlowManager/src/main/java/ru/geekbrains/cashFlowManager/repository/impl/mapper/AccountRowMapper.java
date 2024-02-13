package ru.geekbrains.cashFlowManager.repository.impl.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.geekbrains.cashFlowManager.dto.AccountDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AccountRowMapper implements RowMapper<AccountDTO> {
    @Override
    public AccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(UUID.fromString(rs.getString("id")));
        accountDTO.setAccountNumber(rs.getString("account_number"));
        accountDTO.setAccountOwnerId(UUID.fromString(rs.getString("account_owner_id")));
        accountDTO.setBalance(rs.getInt("balance"));
        accountDTO.setCurrency(rs.getString("currency"));
        accountDTO.setStatus(rs.getString("status"));
        accountDTO.setCreatedAt(rs.getTimestamp("created_at"));
        return accountDTO;
    }
}
