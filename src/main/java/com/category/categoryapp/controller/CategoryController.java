package com.category.categoryapp.controller;

import com.category.categoryapp.model.Category;
import com.category.categoryapp.service.CategoryServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryServiceImpl categoryService;
    private ObjectMapper objectMapper;
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService, ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.categoryService = categoryService;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
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
            value = "/database/category/{categoryId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Category findById(@PathVariable int categoryId) {
        return categoryService.findById(categoryId);
    }

    @RequestMapping(
            value = "/database/category/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Category> findAll() {
        return categoryService.findAll();
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

    @RequestMapping(
            value = "/category/publish",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String publish(){
        Category category = new Category(
                1,
                "Phone",
                "Samsung bro"
        );
        String json = null;
        try {
            json = objectMapper.writeValueAsString(category);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send("categories", json);
        return "Oke";
    }
}
