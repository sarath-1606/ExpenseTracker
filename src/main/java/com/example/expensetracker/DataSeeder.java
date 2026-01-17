// java
package com.example.expensetracker;

import com.example.expensetracker.models.Category;
import com.example.expensetracker.models.Expense;
import com.example.expensetracker.repositories.CategoryRespository;
import com.example.expensetracker.repositories.ExpenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRespository categoryRespository;
    private final ExpenseRepository expenseRepository;

    public DataSeeder(CategoryRespository categoryRespository, ExpenseRepository expenseRepository) {
        this.categoryRespository = categoryRespository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRespository.count() == 0) {
            Category food = new Category(null, "Food");
            Category travel = new Category(null, "Travel");
            Category utilities = new Category(null, "Utilities");
            categoryRespository.saveAll(Arrays.asList(food, travel, utilities));
        }

        if (expenseRepository.count() == 0) {
            Expense e1 = new Expense();

            e1.setTitle("Lunch at Cafe");
            e1.setCategoryname("Food");
            e1.setAmount(25.50);
            e1.setExpensedate(LocalDate.now().minusDays(1));

            Expense e2 = new Expense();
            e2.setTitle("Taxi Ride");

            e2.setCategoryname("Travel");
            e2.setAmount(120.00);
            e2.setExpensedate(LocalDate.now().minusDays(3));

            Expense e3 = new Expense();
            e3.setTitle("Electricity Bill");

            e3.setCategoryname("Utilities");
            e3.setAmount(80.75);
            e3.setExpensedate(LocalDate.now());

            expenseRepository.saveAll(Arrays.asList(e1, e2, e3));
        }
    }
}
