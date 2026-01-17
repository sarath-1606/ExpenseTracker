package com.example.expensetracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expenses")
public class Expense {


    @Id
    private String id;
    private String userId;
    private String categoryId;
    private double amount;
    private LocalDate expensedate;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
