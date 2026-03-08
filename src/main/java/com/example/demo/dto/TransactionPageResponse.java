package com.example.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionPageResponse {

    private List<TransactionResponse> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
