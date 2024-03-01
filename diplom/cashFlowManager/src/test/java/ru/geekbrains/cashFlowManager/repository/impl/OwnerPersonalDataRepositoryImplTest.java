package ru.geekbrains.cashFlowManager.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.geekbrains.cashFlowManager.dto.OwnerPersonalDataDTO;
import ru.geekbrains.cashFlowManager.repository.OwnerPersonalDataRepository;
import ru.geekbrains.cashFlowManager.utils.OwnerDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OwnerPersonalDataRepositoryImplTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void canAdd() {
        String id1 = "3fa85f64-5717-4562-b3fc-2c963f66a150";
        OwnerPersonalDataDTO ownerPersonalDataDTO1 = OwnerDataGenerator.generateOwnerPersonalDataDTO(id1);

        String id2 = "3fa85f64-5717-4562-b3fc-2c963f66a151";
        OwnerPersonalDataDTO ownerPersonalDataDTO2 = OwnerDataGenerator.generateOwnerPersonalDataDTO(id2);

        OwnerPersonalDataRepository ownerPersonalDataRepository = new OwnerPersonalDataRepositoryImpl(jdbcTemplate);

        String id1DB = ownerPersonalDataRepository.add(ownerPersonalDataDTO1);
        String id2DB = ownerPersonalDataRepository.add(ownerPersonalDataDTO2);

        Assertions.assertNotNull(id1DB);
        Assertions.assertNotNull(id1DB);
        Assertions.assertNotEquals(id1DB, id2DB);
    }
}