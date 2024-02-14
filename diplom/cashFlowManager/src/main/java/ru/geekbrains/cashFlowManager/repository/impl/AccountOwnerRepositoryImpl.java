package ru.geekbrains.cashFlowManager.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.geekbrains.cashFlowManager.dto.AccountOwnerDTO;
import ru.geekbrains.cashFlowManager.repository.AccountOwnerRepository;
import ru.geekbrains.cashFlowManager.repository.impl.mapper.AccountOwnerRowMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountOwnerRepositoryImpl implements AccountOwnerRepository {
    // language=sql
    private final static String FIND_ALL_ACCOUNT_OWNERS_SQL = "SELECT id, owner_personal_data_id, status, created_at FROM ACCOUNT_OWNERS";
    // language=sql
    private final static String INSERT_ACCOUNT_OWNER_SQL = "INSERT INTO ACCOUNT_OWNERS (id, owner_personal_data_id, status, created_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
    // language=sql
    private final static String UPDATE_ACCOUNT_OWNER_SQL = "UPDATE ACCOUNT_OWNERS SET owner_personal_data_id = ?, status = ? WHERE id = ?";
    // language=sql
    private final static String FIND_ACCOUNT_OWNER_BY_ID_SQL = "SELECT id, owner_personal_data_id, status, created_at FROM ACCOUNT_OWNERS WHERE id = ?";
    // language=sql
    private final static String DELETE_ACCOUNT_OWNER_SQL = "DELETE FROM ACCOUNT_OWNERS WHERE id = ?";

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
            ps.setString(1, newAccountOwnerDTO.getOwnerPersonalDataId());
            ps.setString(2, newAccountOwnerDTO.getStatus());
            ps.setString(3, newAccountOwnerDTO.getId());
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
            ps.setString(1, id);
            return ps;
        });
        return id;
    }
}