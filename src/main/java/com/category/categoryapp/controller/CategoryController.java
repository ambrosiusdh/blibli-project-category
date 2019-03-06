package com.category.categoryapp.controller;

import com.category.categoryapp.model.Category;
import com.category.categoryapp.service.CategoryServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(
            value = "/category",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Category createMember(@RequestBody Category category) {
        categoryService.createCategory(category);
        return category;
    }

    @RequestMapping(
            value = "/category/{categoryId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Category findById(@PathVariable int categoryId) {
        return categoryService.findById(categoryId);
    }

    @RequestMapping(
            value = "/category",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Category> findAll() {
        return categoryService.getCategories();
    }

    @RequestMapping(
            value = "/category",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Category update(@RequestBody Category category) {
        return categoryService.update(category);
    }

    @RequestMapping(
            value = "/category/{categoryId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Category delete(@PathVariable int categoryId) {
        return categoryService.delete(categoryId);
    }
}
