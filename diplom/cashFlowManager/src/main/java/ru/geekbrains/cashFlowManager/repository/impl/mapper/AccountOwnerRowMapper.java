package ru.geekbrains.cashFlowManager.repository.impl.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.geekbrains.cashFlowManager.dto.AccountOwnerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AccountOwnerRowMapper implements RowMapper<AccountOwnerDTO> {
    @Override
    public AccountOwnerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountOwnerDTO accountOwnerDTO = new AccountOwnerDTO();
        accountOwnerDTO.setId(UUID.fromString(rs.getString("id")));
        accountOwnerDTO.setOwnerPersonalDataId(UUID.fromString(rs.getString("owner_personal_data_id")));
        accountOwnerDTO.setStatus(rs.getString("status"));
        accountOwnerDTO.setCreatedAt(rs.getTimestamp("created_at"));
        return accountOwnerDTO;
    }
}