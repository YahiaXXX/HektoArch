package com.example.msclientqueries.APIs;

import com.example.msclientqueries.DAOs.CategoryDAO;
import com.example.msclientqueries.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryAPI {

    @Autowired
    public CategoryService categoryService;

    @GetMapping("/getAllCategoryies")
    public List<CategoryDAO> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/getCategoryById/{categororyId}")
    public CategoryDAO getCategoryById(@PathVariable String categororyId) {
        return categoryService.getCategoryById(categororyId);
    }

    @GetMapping("/getCategoryByName/{categororyName}")
    public Optional<CategoryDAO> getCategoryByName(@PathVariable String categororyName) {
        return categoryService.getCategoryByName(categororyName);
    }
}
