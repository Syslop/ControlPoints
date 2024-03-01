package ru.geekbrains.cashFlowManager.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.geekbrains.cashFlowManager.dto.AccountOwnerDTO;
import ru.geekbrains.cashFlowManager.dto.OwnerPersonalDataDTO;
import ru.geekbrains.cashFlowManager.repository.AccountOwnerRepository;
import ru.geekbrains.cashFlowManager.repository.impl.mapper.AccountOwnerRowMapper;
import ru.geekbrains.cashFlowManager.repository.impl.mapper.OwnerPersonalDataRowMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountOwnerRepositoryImpl implements AccountOwnerRepository {
    // language=sql
    private final static String FIND_ALL_ACCOUNT_OWNERS_SQL = "SELECT id, owner_personal_data_id, status, created_at FROM ACCOUNT_OWNERS WHERE status != 'DELETED'";
    // language=sql
    private final static String INSERT_ACCOUNT_OWNER_SQL = "INSERT INTO ACCOUNT_OWNERS (id, owner_personal_data_id, status, created_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
    // language=sql
    private final static String UPDATE_ACCOUNT_OWNER_SQL = "UPDATE ACCOUNT_OWNERS SET status = ? WHERE id = ?";
    // language=sql
    private final static String FIND_ACCOUNT_OWNER_BY_ID_SQL = "SELECT id, owner_personal_data_id, status, created_at FROM ACCOUNT_OWNERS WHERE id = ? AND status != 'DELETED'";
    // language=sql
    private final static String DELETE_ACCOUNT_OWNER_SQL = "UPDATE ACCOUNT_OWNERS SET status = ? WHERE id = ?";
    // language=sql
    private final static String FIND_ALL_DELETED_OWNER_PERSONAL_DATA_SQL = "SELECT OPD.id, OPD.owner_surname, OPD.owner_name, OPD.owner_patronymic, OPD.email, OPD.phone_number, OPD.address, OPD.date_of_birth, OPD.gender, AO.status, AO.created_at FROM OWNER_PERSONAL_DATA OPD LEFT JOIN ACCOUNT_OWNERS AO ON AO.owner_personal_data_id = OPD.id WHERE AO.status = 'DELETED'";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountOwnerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String add(AccountOwnerDTO accountOwnerDTO) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(INSERT_ACCOUNT_OWNER_SQL);
            ps.setString(1, accountOwnerDTO.getId());
            ps.setString(2, accountOwnerDTO.getOwnerPersonalDataId());
            ps.setString(3, accountOwnerDTO.getStatus());
            return ps;
        });
        return accountOwnerDTO.getId();
    }

    @Override
    public Boolean edit(AccountOwnerDTO newAccountOwnerDTO) {
        int rowsAffected = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(UPDATE_ACCOUNT_OWNER_SQL);
            ps.setString(1, newAccountOwnerDTO.getStatus());
            ps.setString(2, newAccountOwnerDTO.getId());
            return ps;
        });
        return rowsAffected > 0;
    }

    @Override
    public Optional<AccountOwnerDTO> get(String id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                FIND_ACCOUNT_OWNER_BY_ID_SQL,
                new AccountOwnerRowMapper(),
                id
        ));
    }

    @Override
    public List<AccountOwnerDTO> findAll() {
        return jdbcTemplate.query(FIND_ALL_ACCOUNT_OWNERS_SQL, new AccountOwnerRowMapper());
    }

    @Override
    public String delete(String id) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con
                    .prepareStatement(DELETE_ACCOUNT_OWNER_SQL);
            ps.setString(1, "DELETED");
            ps.setString(2, id);
            return ps;
        });
        return id;
    }

    @Override
    public List<OwnerPersonalDataDTO> findDeletedAccountList() {
        return jdbcTemplate.query(
                FIND_ALL_DELETED_OWNER_PERSONAL_DATA_SQL,
                new OwnerPersonalDataRowMapper()
        );
    }
}