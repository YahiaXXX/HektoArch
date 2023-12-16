package com.example.coreapic.Commands.CategoriesCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryCommand {
    private String categoryId;
    private String categoryName;

}
