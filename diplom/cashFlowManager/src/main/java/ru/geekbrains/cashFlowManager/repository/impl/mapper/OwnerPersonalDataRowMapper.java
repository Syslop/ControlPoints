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
        ownerPersonalDataDTO.setId(rs.getString(1));
        ownerPersonalDataDTO.setOwnerSurname(rs.getString(2));
        ownerPersonalDataDTO.setOwnerName(rs.getString(3));
        ownerPersonalDataDTO.setOwnerPatronymic(rs.getString(4));
        ownerPersonalDataDTO.setEmail(rs.getString(5));
        ownerPersonalDataDTO.setPhoneNumber(rs.getString(6));
        ownerPersonalDataDTO.setAddress(rs.getString(7));
        ownerPersonalDataDTO.setDateOfBirth(rs.getTimestamp(8));
        ownerPersonalDataDTO.setGender(rs.getString(9));
        ownerPersonalDataDTO.setStatus(rs.getString(10));
        ownerPersonalDataDTO.setCreated(rs.getTimestamp(11));
        return ownerPersonalDataDTO;
    }
}