package com.example.msclientcommands.API;

import com.example.coreapi.Commands.PostCommands.UpdatePostCommand;
import com.example.coreapi.Commands.ShoppingCartCommands.CreateShoppingCartCommand;
import com.example.coreapi.Commands.ShoppingCartCommands.EmptyShoppingCartCommand;
import com.example.coreapi.DTOs.PostDTO;
import com.example.coreapi.Events.ShoppingCartEvents.EmptyShoppingCartEvent;
import com.example.msclientcommands.Proxies.MsAuthProxy;
import com.example.msclientcommands.Proxies.ShoppingCartProxy;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController

@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private MsAuthProxy msAuthProxy;

    @Autowired
    private ShoppingCartProxy shoppingCartProxy;


    private final CommandGateway commandGateway;
    public ShoppingCartController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //CREATE
    @PostMapping("/create")
    public ResponseEntity<String> createPost(@CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        Object responseBody = msAuthProxy.getcurrentUser(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("does the response conatins userId: " + responseBody.toString().contains("userId"));
            String extractedSubstringWithRange = responseBody.toString().substring(8, 44);
            System.out.println(responseBody);
            System.out.println(extractedSubstringWithRange);
            System.out.println("before create post command");
            System.out.println(shoppingCartProxy.getShoppingCartByClientId(extractedSubstringWithRange).toString());
            if (shoppingCartProxy.getShoppingCartByClientId(extractedSubstringWithRange).get().getShoppingCartId()==null) {
                CreateShoppingCartCommand command = new CreateShoppingCartCommand(
                        UUID.randomUUID().toString(),
                        extractedSubstringWithRange);
                commandGateway.send(command);
                return ResponseEntity.ok("ShoppingCart Create command sent.");
            }else {
                return ResponseEntity.ok("ShoppingCart already created");

            }
        }
        return ResponseEntity.ok("Please login to continue");

    }


    //UPDATE
    @PatchMapping("/update/empty/{shoppingCartsId}")
    public ResponseEntity<String> EmptyShoppingCart(@PathVariable String shoppingCartsId,@CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        Object responseBody = msAuthProxy.getcurrentUser(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("does the response conatins userId: " + responseBody.toString().contains("userId"));
            String extractedSubstringWithRange = responseBody.toString().substring(8, 44);
            System.out.println(responseBody);
            System.out.println(extractedSubstringWithRange);
            System.out.println("before create post command");

            if (shoppingCartProxy.getShoppingCartByClientId(extractedSubstringWithRange).get().getClientId() != null) {
                EmptyShoppingCartCommand command = new EmptyShoppingCartCommand(
                        shoppingCartsId,
                        extractedSubstringWithRange);
                commandGateway.send(command);

                return ResponseEntity.ok("ShoppingCart Empty command sent.");
            }
        }
        return ResponseEntity.ok("Please login to continue");

    }

}

