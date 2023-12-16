package com.example.coreapic.Commands.CategoriesCommands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateVersion;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeleteCategoryCommand {

    private String categoryId;
    private String categoryName;

}
