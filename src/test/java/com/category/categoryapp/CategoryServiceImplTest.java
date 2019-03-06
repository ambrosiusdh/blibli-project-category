package com.category.categoryapp;

import com.category.categoryapp.model.Category;
import com.category.categoryapp.service.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;

@Component
public class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;
    private Category category1;
    private Category category2;

    @Before
    public void setUp() throws Exception {
        categoryService = new CategoryServiceImpl();
        category1 = new Category(1,
                "Food",
                "Delicious");
        category2 = new Category(2,
                "Samsung",
                "Made by samsoeng");
        categoryService.createCategory(category1);
        categoryService.createCategory(category1);
    }

    @Test
    public void createMember() {
        assertEquals("Data harus 2", categoryService.findAll().size(), 2);

        Category temp = categoryService.findById(1);
        assertEquals("Data harus sama", category1, temp);
    }

    @Test
    public void findById() {

        assertNull("Data harus tidak tertemukan", categoryService.findById(3));

        assertEquals("Data harus sama", categoryService.findById(1), category1);

    }

    @Test
    public void findAll() {
        assertEquals("List harus bersize 2", categoryService.getCategories().size(), 2);
    }

    @Test
    public void update() {
        Category temp = new Category(1,
                "Italian Food",
                "Made by our Italian chef");
        categoryService.update(temp);

        assertEquals("Category harus terupdate", temp, categoryService.findById(1));
    }

    @Test
    public void delete() {
        categoryService.delete(2);
        assertFalse("Category 2 harus terhapus", categoryService.getCategories().contains(category2));

    }
}