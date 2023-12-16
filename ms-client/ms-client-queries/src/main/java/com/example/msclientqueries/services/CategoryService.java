package com.example.msclientqueries.Services;

import com.example.msclientqueries.DAOs.CategoryDAO;
import com.example.msclientqueries.Proxies.CategoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CategoryService {

    @Autowired
    private CategoryProxy categoryProxy;

    public List<CategoryDAO> getAllCategories() {
        return categoryProxy.getAllCategories();
    }

    public CategoryDAO getCategoryById(String categoryId) {
        return categoryProxy.getCategoryById(categoryId);
    }
    public Optional<CategoryDAO> getCategoryByName(String categorysName) {
        return categoryProxy.getCategoryByCategoryName(categorysName);
    }
}
