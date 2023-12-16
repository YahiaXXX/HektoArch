package com.example.msadminqueries.Services;

import com.example.msadminqueries.DAOs.CategoryDAO;
import com.example.msadminqueries.Entities.CategoryEntity;
import com.example.msadminqueries.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepository categoryRepository;

    public List<CategoryDAO> getAllCategories() {
        try {
            List<CategoryDAO> categoryDAOList = new ArrayList<>();
            List<CategoryEntity> categoryEntities = categoryRepository.findAll();

            categoryEntities.forEach(category -> {
                CategoryDAO categoryDAO = new CategoryDAO();
                categoryDAO.setCategoryId(category.getCategoryId());
                categoryDAO.setCategoryName(category.getCategoryName());
                categoryDAOList.add(categoryDAO);
            });

            return categoryDAOList;
        }catch (Exception e){
            return  new ArrayList<>();
        }

    }

    public Optional<CategoryDAO> getCategoryById(String categoryId) {
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            if (categoryRepository.findById(categoryId).isPresent()) {
                CategoryEntity category = categoryRepository.findById(categoryId).get();
                categoryDAO.setCategoryId(category.getCategoryId());
                categoryDAO.setCategoryName(category.getCategoryName());
            } else {
                System.out.println("******* This categorty " + categoryId + " doesn't existe ***********");

            }

            return Optional.of(categoryDAO);
        }catch (Exception e){
            return Optional.empty();
        }

    }

    public Optional<CategoryDAO> getCategoryByCategoryName(String categoryName) {
        try {
            CategoryDAO categoryDAO = new CategoryDAO();

            Optional<CategoryEntity> category = categoryRepository.findCategoryEntityByCategoryName(categoryName);
            if(category.isPresent()) {
                categoryDAO.setCategoryId(category.get().getCategoryId());
                categoryDAO.setCategoryName(category.get().getCategoryName());
            }
            return Optional.of(categoryDAO);
        }catch (Exception e){
            return Optional.empty();
        }

    }


    public void addCategory(CategoryEntity category) {
        try {
            categoryRepository.save(category);

        }catch (Exception e){

        }
    }

    public void updateCategory(String categoryId, String categoryName     ) {
        try {
            String newCategoryId = categoryId.replace("\"", "");
            if (categoryRepository.findById(newCategoryId).isPresent()) {
                CategoryEntity category = new CategoryEntity(
                        newCategoryId,
                        categoryName);
                // categoryRepository.delete(categoryRepository.findById(newCategoryId).get());
                categoryRepository.save(category);
            } else {
                System.out.println("******* This categorty " + categoryName + " doesn't existe ***********");

            }
        }catch (Exception e){

        }

    }

    public void deleteCategory(String categoryId) {
        try {
            String newCategoryId = categoryId.replace("\"", "");
            if (categoryRepository.findById(newCategoryId).isPresent()) {
                categoryRepository.delete(categoryRepository.findById(newCategoryId).get());
            } else {
                System.out.println("******* This categorty " + newCategoryId + " doesn't existe ***********");

            }
        }catch (Exception e){

        }

    }
}
