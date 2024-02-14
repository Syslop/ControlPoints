package ru.geekbrains.cashFlowManager.repository.impl.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.geekbrains.cashFlowManager.dto.OwnerPersonalDataDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OwnerPersonalDataRowMapper implements RowMapper<OwnerPersonalDataDTO> {
    @Override
    public OwnerPersonalDataDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        OwnerPersonalDataDTO ownerPersonalDataDTO = new OwnerPersonalDataDTO();
        ownerPersonalDataDTO.setId(UUID.fromString(rs.getString("id")));
        ownerPersonalDataDTO.setOwnerSurname(rs.getString("owner_surname"));
        ownerPersonalDataDTO.setOwnerName(rs.getString("owner_name"));
        ownerPersonalDataDTO.setOwnerPatronymic(rs.getString("owner_patronymic"));
        ownerPersonalDataDTO.setEmail(rs.getString("email"));
        ownerPersonalDataDTO.setPhoneNumber(rs.getString("phone_number"));
        ownerPersonalDataDTO.setAddress(rs.getString("address"));
        ownerPersonalDataDTO.setDateOfBirth(rs.getTimestamp("date_of_birth"));
        ownerPersonalDataDTO.setGender(rs.getString("gender"));
        return ownerPersonalDataDTO;
    }
}