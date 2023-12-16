//package com.example.msadmincommand.API;
//
//
//import com.example.msadmincommand.Proxies.ClientProxy;
//import org.axonframework.commandhandling.gateway.CommandGateway;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//
//@RequestMapping("/clients")
//public class ClientsCommandController {
//
//    @Autowired
//    private ClientProxy clientProxy;
//    private final CommandGateway commandGateway;
//
//    public ClientsCommandController(CommandGateway commandGateway) {
//        this.commandGateway = commandGateway;
//    }
//
//
//    //DELETE
//    @DeleteMapping("/delete/{clientId}")
//    public ResponseEntity<String> deleteClient(@PathVariable String clientId,@CookieValue(value = "token", defaultValue = "No token found in cookie") String token) {
//        if(token.equals("No token found in cookie")){
//            return  ResponseEntity.ok("Please login to continue");
//        }
//        clientProxy.deleteClientByClientId(clientId);
////        DeleteClientCommand command = new DeleteClientCommand(clientDTO.getClientId(),clientDTO.getClientEmail());
////
////        // Send the command
////        commandGateway.send(command);
//
//        return ResponseEntity.ok("Client Delete command sent.");
//    }
//
//
//}
