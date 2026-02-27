package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.enums.AccountType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountRequest {
    
    @NotBlank
    private String name;

    @NotNull
    private AccountType type;

    @NotNull
    @Positive
    private BigDecimal balance;

    @NotBlank
    private String currency;

    @NotNull
    private Long user_id;
}
