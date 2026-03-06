package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.dto.TransactionResponse;
import com.example.demo.enums.TransactionType;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.service.TransactionService;

class TransactionControllerTest {

    private MockMvc mockMvc;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        this.transactionService = mock(TransactionService.class);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new TransactionController(transactionService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void createTransactionAcceptsLegacyCategoryIdAlias() throws Exception {
        TransactionResponse response = new TransactionResponse();
        response.setId(7L);
        response.setType(TransactionType.EXPENSE);
        response.setAmount(new BigDecimal("42.50"));
        response.setDescription("Groceries");
        response.setDate(LocalDate.of(2026, 3, 6));
        response.setPaymentMethod("CARD");
        response.setCategoryId(3L);
        response.setCategoryName("Food");

        when(transactionService.create(argThat(request -> Long.valueOf(3L).equals(request.getCategoryId()))))
                .thenReturn(response);

        mockMvc.perform(post("/api/transactions")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                  "type": "EXPENSE",
                                  "amount": 42.50,
                                  "description": "Groceries",
                                  "date": "2026-03-06",
                                  "category_id": 3,
                                  "paymentMethod": "CARD"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.categoryId").value(3))
                .andExpect(jsonPath("$.categoryName").value("Food"));
    }

    @Test
    void createTransactionRejectsMalformedBodyWithStableErrorPayload() throws Exception {
        mockMvc.perform(post("/api/transactions")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                  "type": "EXPENSE",
                                  "amount":
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_REQUEST_BODY"))
                .andExpect(jsonPath("$.message").value("Request body is missing or malformed"))
                .andExpect(jsonPath("$.path").value("/api/transactions"));
    }
}
