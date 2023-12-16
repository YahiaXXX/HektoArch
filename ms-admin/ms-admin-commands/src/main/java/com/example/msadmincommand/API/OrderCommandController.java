package com.example.msadmincommand.API;

import com.example.msadmincommand.Proxies.OrderProxy;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/orders")

public class OrderCommandController {


    @Autowired
    private OrderProxy orderProxy;
    private final CommandGateway commandGateway;

    public OrderCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

   // DELETE
    @DeleteMapping("/deleteByAdmin/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable String orderId,@CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
        if(token.equals("No token found in cookie")){
            return  ResponseEntity.ok("Please login to continue");
        }
        String s= orderProxy.deleteOrderByOrderIdByAdmin(orderId);
        return ResponseEntity.ok(s);
    }

}


