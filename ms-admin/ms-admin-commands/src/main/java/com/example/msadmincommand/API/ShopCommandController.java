//package com.example.msadmincommand.API;
//
//import com.example.msadmincommand.Proxies.ShopServiceProxy;
//import org.axonframework.commandhandling.gateway.CommandGateway;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//
//@RequestMapping("/shops")
//public class ShopCommandController {
//
//    @Autowired
//    private ShopServiceProxy shopServiceProxy;
//
//    private final CommandGateway commandGateway;
//
//    public ShopCommandController(CommandGateway commandGateway) {
//        this.commandGateway = commandGateway;
//    }
//
//
//
//    //DELETE
//    @DeleteMapping("/deleteByAdmin/{shopId}")
//    public ResponseEntity<String> deleteByAdmin(@PathVariable String shopId,@CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
//        if(token.equals("No token found in cookie")){
//            return  ResponseEntity.ok("Please login to continue");
//        }
////        String newShopId = shopId.replace("\"", "");
////        shopServiceProxy.deleteShopByShopId(newShopId);
//
////        DeleteShopCommand command = new DeleteShopCommand(shopId);
////
////        // Send the command
////        commandGateway.send(command);
//
//        String s= shopServiceProxy.deleteShopByShopIdByAdmin(shopId);
//        return ResponseEntity.ok(s);
//
//    }
//}
