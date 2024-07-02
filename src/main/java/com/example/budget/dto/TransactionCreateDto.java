package com.example.budget.dto;


import java.time.LocalDate;

public record TransactionCreateDto(Integer amount, String name, LocalDate date, String description) {
}