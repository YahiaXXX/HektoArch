package com.example.msshopcommands.APIs;

import com.example.coreapis.Commands.ProductCommands.*;
import com.example.coreapis.DTOs.OrderDTO;
import com.example.coreapis.DTOs.ProductDTO;
import com.example.coreapis.DTOs.ProductToMsClient;
import com.example.coreapis.DTOs.ProductUpdateDTO;
import com.example.msshopcommands.Proxies.MsAuthProxy;
import com.example.msshopcommands.Proxies.MsClientCommandsProxy;
import com.example.msshopcommands.Proxies.MsClientQueriesProxy;
import com.example.msshopcommands.Proxies.ProductProxy;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins="http://localhost:3000")
public class ProductController {
    @Autowired
    private MsAuthProxy msAuthProxy;

    private final CommandGateway commandGateway;

    @Autowired
    private ProductProxy productProxy;
    @Autowired
    private MsClientQueriesProxy  msClientQueriesProxy;
    @Autowired
    private MsClientCommandsProxy msClientCommandsProxy;

    public ProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody ProductDTO productDTO, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        if (productDTO.getProductName().isEmpty()) {
            return ResponseEntity.ok("Please give the New Product a Title.");
        }
        if (productDTO.getProductImageUrl().isEmpty()) {
            return ResponseEntity.ok("Please give the New Product an Image.");
        }
        if (productDTO.getCategoryName().isEmpty()) {
            return ResponseEntity.ok("Please give the New Product a Category.");
        }
        if (productDTO.getProductPrice().toString().isEmpty()) {
            return ResponseEntity.ok("Please give the New Product a Price.");
        }

        Object responseBody = msAuthProxy.getcurrentShop(token).getBody();
        //assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("does the response contains userId: " + responseBody.toString().contains("userId"));
            String extractedSubstringWithRange = responseBody.toString().substring(8, 44);
            System.out.println(responseBody);
            System.out.println(extractedSubstringWithRange);
            System.out.println("before create product command");
            CreateProductCommand command = new CreateProductCommand(
                    UUID.randomUUID().toString(),
                    extractedSubstringWithRange,
                    productDTO.getProductName(),
                    productDTO.getProductPrice(),
                    productDTO.getProductQuantity(),
                    productDTO.getProductImageUrl(),
                    productDTO.getProductDesc(),
                    productDTO.getProductName()
            );
            // Send the command
            commandGateway.send(command);
            System.out.println("after create product command");

            return ResponseEntity.ok(command);
        }
        return ResponseEntity.ok("Please login to continue");

    }
    @PatchMapping("/addQuantityToProduct")
    public ResponseEntity<String> addQuantityToProduct(@RequestParam("productId") String productId,@RequestParam("quantity") Integer quantity, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        if (productId.isEmpty()) {
            return ResponseEntity.ok("Please give us the ProductID.");
        }
        if (quantity<0 || quantity.toString().isEmpty()) {
            return ResponseEntity.ok("Please give a Quantity Greater then 0");
        }

        Object responseBody = msAuthProxy.getcurrentShop(token).getBody();
        //assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            if (productProxy.getProductById(productId).get().getProductId() != null) {
                AddQuantityToProductCommand command = new AddQuantityToProductCommand(
                      productId,quantity
                );
                // Send the command
                commandGateway.send(command);
                System.out.println("after AddQuantityToProductCommand ");

                return ResponseEntity.ok("Product creation command sent.");
            }else{
                return ResponseEntity.ok("The Product Does not Exist");

            }
        }
        return ResponseEntity.ok("Please login to continue");
    }

    //UPDATE
    @PatchMapping("/update/{productId}")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductUpdateDTO productUpdateDTO, @PathVariable String productId, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }

        if (productUpdateDTO.getProductName().isEmpty()) {
            return ResponseEntity.ok("Please give the Product a Title.");
        }
        if (productUpdateDTO.getProductImageUrl().isEmpty()) {
            return ResponseEntity.ok("Please give the Product an Image.");
        }
        if (productUpdateDTO.getCategoryName().isEmpty()) {
            return ResponseEntity.ok("Please give the Product a Category.");
        }
        if (productUpdateDTO.getProductPrice().toString().isEmpty()) {
            return ResponseEntity.ok("Please give the Product a Price.");
        }
        Object responseBody = msAuthProxy.getcurrentShop(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("does the response contains userId: " + responseBody.toString().contains("userId"));
            String extractedSubstringWithRange = responseBody.toString().substring(8, 44);
            System.out.println(responseBody);
            System.out.println(extractedSubstringWithRange);
            if (productProxy.getProductById(productId).get().getProductId() != null) {
                UpdateProductCommand command = new UpdateProductCommand(
                        productId,
                        productUpdateDTO.getShopId(),
                        productUpdateDTO.getProductName(),
                        productUpdateDTO.getProductPrice(),
                        productUpdateDTO.getProductQuantity(),
                        productUpdateDTO.getProductImageUrl(),
                        productUpdateDTO.getProductDesc(),
                        //productUpdateDTO.getPriceBefore(),
                        productUpdateDTO.getCategoryName()
                        //productUpdateDTO.getOrderId()
                        );
                // Send the command
                commandGateway.send(command);

                return ResponseEntity.ok(command);
            }
            return ResponseEntity.ok("Product does not exist.");
        }
        return ResponseEntity.ok("Please login to continue");

    }

    @PostMapping("/removeProductsFromOrder/{orderId}")
    public ResponseEntity<String> removeProductsFromOrder(@PathVariable String orderId) {
                if(orderId.isEmpty()){
                    return ResponseEntity.ok("Please give a valid orderId");
                }
                RemoveProductsFromOrderCommand command = new RemoveProductsFromOrderCommand(orderId);
                // Send the command
                commandGateway.send(command);

                return ResponseEntity.ok("removeProductsFromOrderCommand sent it");

    }
    @PatchMapping("/addProductToOrder/{orderId}")
    public ResponseEntity<String> addProductToOrder(@RequestBody ProductToMsClient productToMsClient, @PathVariable String orderId, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }

        if (productToMsClient.getProductName().isEmpty()) {
            return ResponseEntity.ok("Please give the Product a Title.");
        }
        if (productToMsClient.getCategoryName().isEmpty()) {
            return ResponseEntity.ok("Please give the Product a Category.");
        }
        if (productToMsClient.getProductPrice().toString().isEmpty()) {
            return ResponseEntity.ok("Please give the Product a Price.");
        }
        Object responseBody = msAuthProxy.getcurrentShop(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("does the response contains userId: " + responseBody.toString().contains("userId"));
            String extractedSubstringWithRange = responseBody.toString().substring(8, 44);
            System.out.println(responseBody);
            System.out.println(extractedSubstringWithRange);
            if (productProxy.getProductById(productToMsClient.getProductId()).get().getProductId() != null) {
                if(productProxy.getProductById(productToMsClient.getProductId()).get().getProductQuantity()< productToMsClient.getProductQuantity()){
                    return ResponseEntity.ok("There is no enough Quantity From this Product, we're sorry");
                }
                OrderDTO orderDTO=msClientQueriesProxy.getOrderById(orderId);
                if(Objects.equals(orderDTO.getOrderId(), orderId)){
                    try{
                        msClientCommandsProxy.addProductToOrder(productToMsClient,orderId);
                        AddProductToOrderCommand command = new AddProductToOrderCommand(
                                productToMsClient.getProductId(),
                                productToMsClient.getProductQuantity(),
                                orderDTO
                                //productUpdateDTO.getOrderId()
                        );
                        // Send the command
                        commandGateway.send(command);

                        return ResponseEntity.ok("Product Update command sent.");
                    }catch(Exception e) {
                        System.out.println(e);
                        return ResponseEntity.ok("Error while sending addProductToOrder command to ms-client.");
                    }

                }else {
                    return ResponseEntity.ok("Order does not exist.");

                }

            }
            return ResponseEntity.ok("Product does not exist.");
        }
        return ResponseEntity.ok("Please login to continue");

    }
    @PatchMapping("/addProductsToOrder/{orderId}")
    public ResponseEntity<String> addProductsToOrder(@RequestBody List<ProductToMsClient> productToMsClients, @PathVariable String orderId, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (!token.equals("No token found in cookie")) {

        for(int i=0;i<productToMsClients.size();++i){
            if (productToMsClients.get(i).getProductName().isEmpty()) {
                return ResponseEntity.ok("Please give the Product N:"+(++i)+" a Title.");
            }
            if (productToMsClients.get(i).getCategoryName().isEmpty()) {
                return ResponseEntity.ok("Please give the Product N:"+(++i)+" a Category.");
            }
            if (productToMsClients.get(i).getProductPrice().toString().isEmpty()) {
                return ResponseEntity.ok("Please give the Product N:"+(++i)+" a Price.");
            }
        }

        Object responseBody = msAuthProxy.getcurrentShop(token).getBody();
        assert responseBody != null;
        if (!(Objects.equals(responseBody.toString(), "No token found in cookie"))) {
            System.out.println("does the response contains userId: " + responseBody.toString().contains("userId"));
            String extractedSubstringWithRange = responseBody.toString().substring(8, 44);
            System.out.println(responseBody);
            System.out.println(extractedSubstringWithRange);
            OrderDTO orderDTO=msClientQueriesProxy.getOrderById(orderId);
            if(Objects.equals(orderDTO.getOrderId(), orderId)) {
                List<String> productsIds=new ArrayList<>();
                List<Integer> productsQuantities=new ArrayList<>();
                for(int i=0;i<productToMsClients.size();i++){
                    if (productProxy.getProductById(productToMsClients.get(i).getProductId()).get().getProductId() != null) {
                        if(productProxy.getProductById(productToMsClients.get(i).getProductId()).get().getProductQuantity()< productToMsClients.get(i).getProductQuantity()){
                            return ResponseEntity.ok("There is no enough Quantity From Product N: "+(++i)+", we're sorry");
                        }
                        productsIds.add(productToMsClients.get(i).getProductId());
                        productsQuantities.add(productToMsClients.get(i).getProductQuantity());
                    }else {
                        productsIds.clear();
                        productsQuantities.clear();
                        return ResponseEntity.ok("The Product N: "+(++i)+" does not exist.");
                    }
                }

                if (!productsIds.isEmpty()) {
                        try{
                            msClientCommandsProxy.addProductsToOrder(productToMsClients,orderId);
                            AddProductsToOrderCommand command = new AddProductsToOrderCommand(
                                    productsIds,
                                    productsQuantities,
                                    orderDTO
                                    //productUpdateDTO.getOrderId()
                            );
                            // Send the command
                            commandGateway.send(command);

                            return ResponseEntity.ok("Products Update command sent.");
                        }catch(Exception e) {
                            System.out.println(e);
                            return ResponseEntity.ok("Error while sending addProductToOrder command to ms-client.");
                        }

                    }

                }    else {
                return ResponseEntity.ok("Order does not exist.");
            }

        }
        }
        return ResponseEntity.ok("Please login to continue");

    }

    //DELETE
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId, @CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {

        if (token.equals("No token found in cookie")) {
            return ResponseEntity.ok("Please login to continue");
        }
        if (productProxy.getProductById(productId).get().getProductId() != null) {
            DeleteProductCommand command = new DeleteProductCommand(productId);

            // Send the command
            commandGateway.send(command);
            System.out.println("********** after send the Command **********");

            return ResponseEntity.ok("Product Delete command sent.");
        } else {
            return ResponseEntity.ok("Product does not exist.");
        }
    }

}
