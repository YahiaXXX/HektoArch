package com.example.coreapic.Events.CategoriesEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryEvent {
    private String eventIdentifier;
    private String categoryId;
    private String categoryName;

}
