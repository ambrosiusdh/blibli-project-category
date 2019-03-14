package com.category.categoryapp.repository;

import com.category.categoryapp.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, Integer> {


}
