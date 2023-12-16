package com.example.coreapic.Events.CategoriesEvents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryEvent {
    private String eventIdentifier;

    private String categoryId;
    private String categoryName;

}
