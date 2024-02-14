package ru.geekbrains.cashFlowManager.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.geekbrains.cashFlowManager.dto.OwnerPersonalDataDTO;
import ru.geekbrains.cashFlowManager.repository.OwnerPersonalDataRepository;
import ru.geekbrains.cashFlowManager.repository.impl.mapper.OwnerPersonalDataRowMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OwnerPersonalDataRepositoryImpl implements OwnerPersonalDataRepository {
    // language=sql
    private final static String FIND_ALL_OWNER_PERSONAL_DATA_SQL = "SELECT id, owner_surname, owner_name, owner_patronymic, email, phone_number, address, date_of_birth, gender FROM OWNER_PERSONAL_DATA";
    // language=sql
    private final static String INSERT_OWNER_PERSONAL_DATA_SQL = "INSERT INTO OWNER_PERSONAL_DATA (id, owner_surname, owner_name, owner_patronymic, email, phone_number, address, date_of_birth, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // language=sql
    private final static String UPDATE_OWNER_PERSONAL_DATA_SQL = "UPDATE OWNER_PERSONAL_DATA SET owner_surname = ?, owner_name = ?, owner_patronymic = ?, email = ?, phone_number = ?, address = ?, date_of_birth = ?, gender = ? WHERE id = ?";
    // language=sql
    private final static String FIND_OWNER_PERSONAL_DATA_BY_ID_SQL = "SELECT id, owner_surname, owner_name, owner_patronymic, email, phone_number, address, date_of_birth, gender FROM OWNER_PERSONAL_DATA WHERE id = ?";
    // language=sql
    private final static String DELETE_OWNER_PERSONAL_DATA_SQL = "DELETE FROM OWNER_PERSONAL_DATA WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OwnerPersonalDataRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID add(OwnerPersonalDataDTO ownerPersonalDataDTO) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_OWNER_PERSONAL_DATA_SQL);
            ps.setObject(1, ownerPersonalDataDTO.getId());
            ps.setString(2, ownerPersonalDataDTO.getOwnerSurname());
            ps.setString(3, ownerPersonalDataDTO.getOwnerName());
            ps.setString(4, ownerPersonalDataDTO.getOwnerPatronymic());
            ps.setString(5, ownerPersonalDataDTO.getEmail());
            ps.setString(6, ownerPersonalDataDTO.getPhoneNumber());
            ps.setString(7, ownerPersonalDataDTO.getAddress());
            ps.setTimestamp(8, ownerPersonalDataDTO.getDateOfBirth());
            ps.setString(9, ownerPersonalDataDTO.getGender());
            return ps;
        });
        return ownerPersonalDataDTO.getId();
    }

    @Override
    public Boolean edit(OwnerPersonalDataDTO newOwnerPersonalDataDTO) {
        int rowsAffected = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(UPDATE_OWNER_PERSONAL_DATA_SQL);
            ps.setString(1, newOwnerPersonalDataDTO.getOwnerSurname());
            ps.setString(2, newOwnerPersonalDataDTO.getOwnerName());
            ps.setString(3, newOwnerPersonalDataDTO.getOwnerPatronymic());
            ps.setString(4, newOwnerPersonalDataDTO.getEmail());
            ps.setString(5, newOwnerPersonalDataDTO.getPhoneNumber());
            ps.setString(6, newOwnerPersonalDataDTO.getAddress());
            ps.setTimestamp(7, newOwnerPersonalDataDTO.getDateOfBirth());
            ps.setString(8, newOwnerPersonalDataDTO.getGender());
            ps.setObject(9, newOwnerPersonalDataDTO.getId());
            return ps;
        });
        return rowsAffected > 0;
    }

    @Override
    public Optional<OwnerPersonalDataDTO> get(String id) {
        return jdbcTemplate.queryForObject(FIND_OWNER_PERSONAL_DATA_BY_ID_SQL, new Object[]{id}, (rs, rowNum) ->
                Optional.ofNullable(new OwnerPersonalDataRowMapper().mapRow(rs, rowNum))
        );
    }

    @Override
    public List<OwnerPersonalDataDTO> findAll() {
        return jdbcTemplate.query(FIND_ALL_OWNER_PERSONAL_DATA_SQL, new OwnerPersonalDataRowMapper());
    }

    @Override
    public UUID delete(String id) {
        jdbcTemplate.update(DELETE_OWNER_PERSONAL_DATA_SQL, id);
        return UUID.fromString(id);
    }
}