package com.category.categoryapp.service;

import com.category.categoryapp.model.Category;
import com.category.categoryapp.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category findById(int id) {
        for (Category c: categoryRepository.findAll()) {
            if(c.getCategoryId() == id){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category update(Category category) {
        for (Category c : categoryRepository.findAll()) {
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
        for (Category c : categoryRepository.findAll()) {
            if(c.getCategoryId() == id){
                BeanUtils.copyProperties(c, temp);
                categoryRepository.delete(c);
                return temp;
            }
        }
        return null;
    }

    public void deleteAll() {
        if(categoryRepository.findAll().size() > 0)
            categoryRepository.deleteAll();
    }
}
