package com.example.expensetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ExpenseRequest {
    private String title;

    private String categoryname;
    private double amount;
    private LocalDate expensedate;

}
