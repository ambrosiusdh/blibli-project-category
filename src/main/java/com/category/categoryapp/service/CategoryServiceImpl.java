package com.category.categoryapp.service;

import com.category.categoryapp.model.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories= new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public Category createCategory(Category category) {
        categories.add(category);
        return category;
    }

    @Override
    public Category findById(int id) {
        for (Category c: categories) {
            if(c.getCategoryId() == id){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public Category update(Category category) {
        for (Category c : categories) {
            if(c.getCategoryId() == category.getCategoryId()){
                BeanUtils.copyProperties(category, c);
                return c;
            }
        }
        return null;
    }

    @Override
    public Category delete(int id) {
        Category temp = new Category();
        for (Category c : categories) {
            if(c.getCategoryId() == id){
                BeanUtils.copyProperties(c, temp);
                categories.remove(c);
                return temp;
            }
        }
        return null;
    }
}
