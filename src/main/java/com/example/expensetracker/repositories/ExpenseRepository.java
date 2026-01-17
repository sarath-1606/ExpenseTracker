package com.example.expensetracker.repositories;

import com.example.expensetracker.models.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ExpenseRepository extends MongoRepository<Expense, String> {
   List< Expense> findByCategoryname(String categoryName);

}
