package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.demo.enums.TransactionType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionRequest {
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate date;

    @NotBlank
    private Long category_id;

    @NotBlank
    private Long user_id;

    @NotBlank
    private String paymentMethod;
}
