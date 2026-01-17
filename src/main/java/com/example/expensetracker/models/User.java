package com.example.expensetracker.models;

import org.springframework.data.annotation.Id;

public class User {


    @Id
    private String id;
    private String name;
    private String email;
    private String password;
}
