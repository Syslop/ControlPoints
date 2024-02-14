package ru.geekbrains.cashFlowManager.repository.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.geekbrains.cashFlowManager.dto.TransactionDTO;
import ru.geekbrains.cashFlowManager.repository.impl.mapper.TransactionRowMapper;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryImplTest {
/*
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TransactionRepositoryImpl transactionRepository;

    @Test
    public void testAdd() {
        // Создание объекта TransactionDTO и установка его значений
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(String.valueOf(UUID.randomUUID()));
        transactionDTO.setAccountId(String.valueOf(UUID.randomUUID()));
        transactionDTO.setAmount(100);
        transactionDTO.setCurrency("USD");
        transactionDTO.setOperationType("DEPOSIT");
        transactionDTO.setTransactionDescription("Test transaction");

        // Вызов метода add() вашего репозитория
        String result = transactionRepository.add(transactionDTO);

        // Проверка результата
        assertEquals(transactionDTO.getId(), result);
    }

    @Test
    public void testFindAll() {
        List<TransactionDTO> transactionDTOList = List.of(
                new TransactionDTO(),
                new TransactionDTO()
        );

        when(jdbcTemplate.query(any(String.class), any(TransactionRowMapper.class)))
                .thenReturn(transactionDTOList);

        List<TransactionDTO> result = transactionRepository.findAll();

        assertEquals(transactionDTOList.size(), result.size());
        verify(jdbcTemplate, times(1)).query(any(String.class), any(TransactionRowMapper.class));
    }

    @Test
    public void testDelete() {
        UUID id = UUID.randomUUID();

        when(jdbcTemplate.update(any(String.class), any(String.class))).thenReturn(1);

        String result = transactionRepository.delete(id.toString());

        assertEquals(id, result);
        verify(jdbcTemplate, times(1)).update(any(String.class), any(String.class));
    }

 */
}
