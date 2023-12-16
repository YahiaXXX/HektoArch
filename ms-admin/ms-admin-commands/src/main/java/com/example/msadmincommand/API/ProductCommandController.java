package com.example.msadmincommand.API;

import com.example.msadmincommand.Proxies.ProductsProxy;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/products")

public class ProductCommandController {


    @Autowired
    private ProductsProxy productProxy;

    private final CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //DELETE
    @DeleteMapping("/deleteByAdmin/{productId}")
    public ResponseEntity<String> deleteProductByAdmin(@PathVariable String productId,@CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
        if(token.equals("No token found in cookie")){
            return  ResponseEntity.ok("Please login to continue");
        }
//        DeleteProductCommand command = new DeleteProductCommand(productId);
//
//        // Send the command
//        commandGateway.send(command);
        String s= productProxy.deleteProductByProductIdByAdmin(productId);
        return ResponseEntity.ok(s);
    }
}


