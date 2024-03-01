package ru.geekbrains.cashFlowManager.openapi.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.cashFlowManager.utils.OwnerDataGenerator;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OwnerApiImplTest {
    String ownerId = "3fa85f64-5717-4562-b3fc-2c963f66afa0";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ownerAddApiTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("http://localhost:8080/owner/add/")
                .content(mapper.writeValueAsString(OwnerDataGenerator.generateAddOwnerRequest(ownerId)))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(status().isOk());
    }
}