package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.enums.AccountType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponse {

    private long id;
    private String name;
    private AccountType type;
    private BigDecimal balance;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
