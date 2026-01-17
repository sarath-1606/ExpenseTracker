package com.example.expensetracker.dto;


import java.time.LocalDate;

public class ExpenseRequest {
    private String UserId;
    private String categoryId;
    private double amount;
    private LocalDate expensedate;
    private String description;
}
