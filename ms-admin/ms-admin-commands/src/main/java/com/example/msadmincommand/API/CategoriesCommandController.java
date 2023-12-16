package com.example.msadmincommand.API;

import com.example.coreapic.Commands.CategoriesCommands.CreateCategoryCommand;
import com.example.coreapic.Commands.CategoriesCommands.DeleteCategoryCommand;
import com.example.coreapic.Commands.CategoriesCommands.UpdateCategoryCommand;
import com.example.coreapic.DTO.CategoryDTO;
import com.example.msadmincommand.Proxies.CategoryProxy;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController

@RequestMapping("/categories")
public class CategoriesCommandController {
    private final CommandGateway commandGateway;

    @Autowired
    private CategoryProxy categoryProxy;
    public CategoriesCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    //CREATE
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        if(categoryDTO.getCategoryName().isEmpty()){
            return ResponseEntity.ok("Please give the New Category a name.");
        }
        if(!Objects.equals(categoryProxy.getCategoryByName(categoryDTO.getCategoryName()).get().getCategoryName(), categoryDTO.getCategoryName())){
            CreateCategoryCommand command = new CreateCategoryCommand(UUID.randomUUID().toString(), categoryDTO.getCategoryName());
            commandGateway.send(command);
            return ResponseEntity.ok("Category creation command sent.");
        }
        else{
            return ResponseEntity.ok("Category already exist.");
        }
    }
    //UPDATE
    @PatchMapping("/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable String categoryId) {
        CategoryDTO categoryDTO1=categoryProxy.getCategoryById(categoryId).get();
        if(categoryDTO1.getCategoryName() != null){
            if(Objects.equals(categoryDTO.getCategoryName(), categoryProxy.getCategoryByName(categoryDTO.getCategoryName()).get().getCategoryName())){
                return ResponseEntity.ok("There is another Category with the same name.");
            }

            UpdateCategoryCommand command = new UpdateCategoryCommand(categoryId, categoryDTO.getCategoryName() );

            // Send the command
            commandGateway.send(command);

            return ResponseEntity.ok("Category Update command sent.");
        }
        else{
            return ResponseEntity.ok("Category does not exist.");
        }
    }

    //DELETE
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryId) {

        if(categoryProxy.getCategoryById(categoryId).get().getCategoryName() != null){

            DeleteCategoryCommand command = new DeleteCategoryCommand(categoryId,"");

            // Send the command
            commandGateway.send(command);
            System.out.println("********** after send the Command **********");

            return ResponseEntity.ok("Category Delete command sent.");
        }
        else{
            return ResponseEntity.ok("Category does not exist.");
        }


    }
}
