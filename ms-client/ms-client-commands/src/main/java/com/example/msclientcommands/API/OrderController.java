package com.example.msclientcommands.API;

import com.example.coreapi.Commands.OrderCommands.*;
import com.example.coreapi.DTOs.OrderDTO;
import com.example.coreapi.DTOs.OrderStatus;
import com.example.coreapi.DTOs.ProductDTO;
import com.example.coreapi.DTOs.UserDTO;
import com.example.msclientcommands.Proxies.MsAuthProxy;
import com.example.msclientcommands.Proxies.ProductCommandProxy;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController

@RequestMapping("/orders")

public class OrderController {

    @Autowired
    private MsAuthProxy msAuthProxy;
    @Autowired
    private ProductCommandProxy productCommandProxy;
    private final CommandGateway commandGateway;

    public OrderController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    //CREATE
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
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

            System.out.println("before creation order command");
            CreateOrderCommand command = new CreateOrderCommand(
                    orderDTO.getOrderDate(),
                    orderDTO.getOrderStatus(),
//                    orderDTO.getShoppingCartId(),
                    extractedSubstringWithRange,
                    orderDTO.getProducts());

            // Send the command
            commandGateway.send(command);
            System.out.println("after creation order command");

            return ResponseEntity.ok("Order creation command sent.");
        }
        return ResponseEntity.ok("Please login to continue");

    }

    //UPDATE
    @PatchMapping("/updateOrder/{orderId}")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDTO orderDTO, @PathVariable String orderId,
                                              @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        Object responseBody = msAuthProxy.getcurrentUser(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("before sent the updateOrder Command ");
            if(orderDTO.getProducts().size()==0){
                return ResponseEntity.ok("Please add some Products to the order Or delete it");
            }
            System.out.println("does the response conatins userId: " + responseBody.toString().contains("userId"));
            String extractedSubstringWithRange = responseBody.toString().substring(8, 44);
            System.out.println(responseBody);
            System.out.println(extractedSubstringWithRange);

            UpdateOrderCommand command = new UpdateOrderCommand(
//                    UUID.randomUUID().toString(),
                            orderId,
                    orderDTO.getOrderDate(),
                    orderDTO.getOrderStatus(),
                    extractedSubstringWithRange,
                    orderDTO.getProducts());
            System.out.println("after create the addProductToOrder Command ");

            // Send the command
            commandGateway.send(command);
            System.out.println("after sent the addProductToOrder Command ");

            return ResponseEntity.ok("addProductToOrder command sent.");
        }
        return ResponseEntity.ok("Please login to continue");
    }

    @PatchMapping("/addProductToOrder/{orderId}")
    public Optional<ResponseEntity<String>> addProductToOrder(@RequestBody ProductDTO productDTO, @PathVariable String orderId, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
        if (token.equals("No token found in cookie")) {
            return Optional.of(ResponseEntity.ok("Please login to continue"));
        }
        Object responseBody = msAuthProxy.getcurrentUser(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("before sent the addProductToOrder Command ");
            AddProductToOrderCommand command = new AddProductToOrderCommand(
//                    UUID.randomUUID().toString(),
                    productDTO.getProductId(),
                    orderId,
                    productDTO.getProductName(),
                    productDTO.getProductPrice(),
                    productDTO.getProductQuantity(),
                    productDTO.getCategoryName());
            System.out.println("after create the addProductToOrder Command ");

            // Send the command
            commandGateway.send(command);
            System.out.println("after sent the addProductToOrder Command ");

            return Optional.of(ResponseEntity.ok("addProductToOrder command sent."));
        }
        return Optional.of(ResponseEntity.ok("Please login to continue"));
    }
    @PostMapping("/addProductToOrderfromShop/{orderId}")
    public Optional<ResponseEntity<String>> addProductToOrderfromShop(@RequestBody ProductDTO productDTO, @PathVariable String orderId) {
            System.out.println("before sent the addProductToOrder Command ");
            AddProductToOrderCommand command = new AddProductToOrderCommand(
//                    UUID.randomUUID().toString(),
                    productDTO.getProductId(),
                    orderId,
                    productDTO.getProductName(),
                    productDTO.getProductPrice(),
                    productDTO.getProductQuantity(),
                    productDTO.getCategoryName());
            System.out.println("after create the addProductToOrder Command ");

            // Send the command
            commandGateway.send(command);
            System.out.println("after sent the addProductToOrder Command ");

            return Optional.of(ResponseEntity.ok("addProductToOrder command sent."));
        }

    @PatchMapping("/addProductsToOrder/{orderId}")
    public ResponseEntity<String> addProductsToOrder(@RequestBody List<ProductDTO> productDTOs, @PathVariable String orderId, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        Object responseBody = msAuthProxy.getcurrentUser(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("before sent the addProductsToOrder Command ");
            AddProductsToOrderCommand command = new AddProductsToOrderCommand(
//                    UUID.randomUUID().toString(),
                    productDTOs,
                    orderId);
            System.out.println("after create the addProductsToOrder Command ");

            // Send the command
            commandGateway.send(command);
            System.out.println("after sent the addProductsToOrder Command ");

            return ResponseEntity.ok("addProductsToOrder command sent.");
        }
        return ResponseEntity.ok("Please login to continue");
    }
    @PostMapping("/addProductsToOrderFromShop/{orderId}")
    public ResponseEntity<String> addProductsToOrder(@RequestBody List<ProductDTO> productDTOs, @PathVariable String orderId) {

            System.out.println("before sent the addProductsToOrder Command ");
            AddProductsToOrderCommand command = new AddProductsToOrderCommand(
//                    UUID.randomUUID().toString(),
                    productDTOs,
                    orderId);
            System.out.println("after create the addProductsToOrder Command ");

            // Send the command
            commandGateway.send(command);
            System.out.println("after sent the addProductsToOrder Command ");

            return ResponseEntity.ok("addProductsToOrder command sent.");

    }
    @PatchMapping("/removeProductFromOrder")
    public ResponseEntity<String> removeProductFromOrder(@RequestParam("orderId") String orderId,@RequestParam("productIdJoinColumn") String productIdJoinColumn, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        Object responseBody = msAuthProxy.getcurrentUser(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {

            RemoveProductFromOrderCommand command = new RemoveProductFromOrderCommand(
//                    UUID.randomUUID().toString(),
                    productIdJoinColumn,
                    orderId);

            // Send the command
            commandGateway.send(command);

            return ResponseEntity.ok("removeProductFromOrder command sent.");
        }
        return ResponseEntity.ok("Please login to continue");
    }

    @PatchMapping("/updateOrderStatus")
    public ResponseEntity<String> updateOrderStatus(@RequestParam("orderId") String orderId, @RequestParam("newOrderStatus") OrderStatus newOrderStatus, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        Object responseBody = msAuthProxy.getcurrentUser(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {

            UpdateOrderStateCommand command = new UpdateOrderStateCommand(
//                    UUID.randomUUID().toString(),
                    orderId,newOrderStatus);

            // Send the command
            commandGateway.send(command);

            return ResponseEntity.ok("UpdateOrderStateCommand sent.");
        }
        return ResponseEntity.ok("Please login to continue");
    }

        //DELETE
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        DeleteOrderCommand command = new DeleteOrderCommand(orderId);
        // Send the command
        commandGateway.send(command);
        productCommandProxy.removeProductsFromOrder(command.getOrderId());

        System.out.println("********** after send the Command **********");

        return ResponseEntity.ok("Order Delete command sent.");
    }
    @DeleteMapping("/deleteByAdmin/{orderId}")
    public ResponseEntity<String> deleteOrderByAdmin(@PathVariable String orderId) {

        DeleteOrderCommand command = new DeleteOrderCommand(orderId);
        // Send the command
        commandGateway.send(command);
        System.out.println("********** after send the Command **********");

        return ResponseEntity.ok("Order Delete command sent.");
    }

}

