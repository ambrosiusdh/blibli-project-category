package com.category.categoryapp.service;

import com.category.categoryapp.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {

    Category createCategory(Category category);
    Category findById(int id);
    List<Category> findAll();
    Category update(Category category);
    Category delete(int id);

}
