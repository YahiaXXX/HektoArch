package com.example.msadminqueries.APIs;

import com.example.msadminqueries.DAOs.CategoryDAO;
import com.example.msadminqueries.Services.CategoryService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins="http://localhost:3000")
public class CategoryAPI {
    @Autowired
    public CategoryService categoryService;

    @Autowired
    private QueryGateway queryGateway;
    @GetMapping("/getAll")
    public List<CategoryDAO> getAllCategories() {
       // return queryGateway.query(new GetAllCategoriesQuery(), ResponseTypes.multipleInstancesOf(CategoryDAO.class));
        return categoryService.getAllCategories();
    }
    @GetMapping("/getCategoryById/{categoryId}")
    public Optional<CategoryDAO> getCategoryById(@PathVariable String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }
    @GetMapping("/getCategoryByName/{categoryName}")
    public Optional<CategoryDAO> getCategoryByCategoryName(@PathVariable String categoryName) {
        return categoryService.getCategoryByCategoryName(categoryName);
    }

}
