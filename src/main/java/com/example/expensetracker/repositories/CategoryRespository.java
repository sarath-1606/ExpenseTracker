package com.example.expensetracker.repositories;

import com.example.expensetracker.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespository extends MongoRepository<Category,String> {


}
