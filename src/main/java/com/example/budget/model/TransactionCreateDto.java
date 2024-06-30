package com.example.budget.model;


import java.time.LocalDate;

public record TransactionCreateDto(Integer amount, String name, LocalDate date, String description) {
}