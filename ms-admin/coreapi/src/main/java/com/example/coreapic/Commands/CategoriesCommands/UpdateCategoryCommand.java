package com.example.coreapic.Commands.CategoriesCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryCommand {

        private String categoryId;
        private String categoryName;


}

