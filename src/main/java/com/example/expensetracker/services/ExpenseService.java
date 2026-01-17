package com.example.expensetracker.services;

import com.example.expensetracker.dto.ExpenseRequest;
import com.example.expensetracker.models.Category;
import com.example.expensetracker.models.Expense;
import com.example.expensetracker.repositories.CategoryRespository;
import com.example.expensetracker.repositories.ExpenseRepository;
import com.example.expensetracker.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRespository categoryRespository;
    private final UserRepository userRepository;


    public ExpenseService(ExpenseRepository expenseRepository, CategoryRespository categoryRespository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRespository = categoryRespository;
        this.userRepository = userRepository;
    }

    public Category addcategory(Category category){
        return(categoryRespository.save(category));

    }
    public List<Category> getAllCategories(){
        return categoryRespository.findAll();
    }
    public Expense addexpense(ExpenseRequest expenseRequest){

        Expense expense=new Expense();

        expense.setCategoryname(expenseRequest.getCategoryname());
        expense.setAmount(expenseRequest.getAmount());
        expense.setExpensedate(expenseRequest.getExpensedate());
        expense.setTitle(expenseRequest.getTitle());
        return (expenseRepository.save(expense));

    }
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }
    public Void deleteexpense(String id){
        expenseRepository.deleteById(id);
        return null;
    }
    public Expense updateexpense(String id, ExpenseRequest expenseRequest){
        Expense expense=expenseRepository.findById(id).orElseThrow(()->new RuntimeException("Expense not found"));
        expense.setCategoryname(expenseRequest.getCategoryname());
        expense.setAmount(expenseRequest.getAmount());
        expense.setTitle(expenseRequest.getTitle());
        expense.setExpensedate(expenseRequest.getExpensedate());
        return expenseRepository.save(expense);
    }
    public List<Expense> getExpensesByCategory(String categoryname) {
        return expenseRepository.findByCategoryname(categoryname);
    }




}
