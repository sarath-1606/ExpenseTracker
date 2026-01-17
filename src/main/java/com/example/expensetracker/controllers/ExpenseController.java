package com.example.expensetracker.controllers;

import com.example.expensetracker.dto.ExpenseRequest;
import com.example.expensetracker.dto.ExpenseResponse;
import com.example.expensetracker.models.Expense;
import com.example.expensetracker.services.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ExpenseController {


    private final ExpenseService expenseService;


    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping("/addexpense")
    public ResponseEntity<Expense> addExpense(@RequestBody ExpenseRequest request){
      Expense expense=expenseService.addexpense( request);
      return ResponseEntity.ok(expense);
    }
    @GetMapping("/getallexpenses")
    public ResponseEntity<List<Expense>> getAllExpenses(){
        List<Expense> expenses=expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);



    }
    @DeleteMapping("/deleteexpense/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable String id){

        expenseService.deleteexpense(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/updateexpense/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable String id,@RequestBody ExpenseRequest expenseRequest){
        Expense updatedExpense=expenseService.updateexpense(id,expenseRequest);
        return ResponseEntity.ok(updatedExpense);}

    @GetMapping("/{categoryname}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable String categoryname){
        List<Expense> expenses=expenseService.getExpensesByCategory(categoryname);
        return ResponseEntity.ok(expenses);
    }









}
