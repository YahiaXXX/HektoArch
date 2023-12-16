package com.example.msadminqueries.Projections;

import com.example.coreapic.Events.CategoriesEvents.DeleteCategoryEvent;
import com.example.coreapic.Events.CategoriesEvents.UpdateCategoryEvent;
import com.example.msadminqueries.DAOs.CategoryDAO;
import com.example.msadminqueries.Entities.CategoryEntity;
import com.example.coreapic.Events.CategoriesEvents.CreateCategoryEvent;
import com.example.msadminqueries.Services.CategoryService;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

@Component
public class CategoryProjection {
    @Autowired
    public CategoryService categoryService;

    @EventHandler
    public void  createCategoryEventHandler(CreateCategoryEvent event)
    {
        System.out.println("********** after send the creation Command and after send the Event **********");
        CategoryEntity category=new CategoryEntity(
                event.getCategoryId(),
                event.getCategoryName()
        );
        categoryService.addCategory(category);
    }
    @EventHandler
    public void  updateCategoryEventHandler(UpdateCategoryEvent event)
    {
        System.out.println("********** after send the UPDATE Command and after send the Event **********");
        categoryService.updateCategory(event.getCategoryId(), event.getCategoryName());
    }
    @EventHandler
    public void  deleteCategoryEventHandler(DeleteCategoryEvent event)
    {
        System.out.println("********** after send the delete Command and after send the Event **********");
        categoryService.deleteCategory(event.getCategoryId());

    }

//    @QueryHandler
//    public List<CategoryDAO> getAllCategories(GetAllCategoriesQuery query) {
//        return categoryService.getAllCategories();
//    }
}
