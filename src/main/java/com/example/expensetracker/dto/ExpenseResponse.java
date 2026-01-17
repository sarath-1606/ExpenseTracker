package com.example.expensetracker.dto;

import java.time.LocalDate;

public class ExpenseResponse {
    private String expenseId;

    private String categoryId;
    private double amount;
    private LocalDate expensedate;
    private String description;
}
