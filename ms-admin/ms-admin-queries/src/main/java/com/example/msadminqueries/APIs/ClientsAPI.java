package com.example.msadminqueries.APIs;

import com.example.msadminqueries.DAOs.ClientDAO;
import com.example.msadminqueries.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/clients")
public class ClientsAPI {

        @Autowired
        public ClientService clientService;
        @GetMapping("/getAllClients")
        public List<ClientDAO> getAllClients() {
            return clientService.getAllClients();
        }

        @GetMapping("/getClientByClientEmail/{clientEmail}")
        public ClientDAO getClientByClientEmail(@PathVariable String clientEmail) {
            return clientService.getClientByClientEmail(clientEmail);
        }
        @GetMapping("/getClientByClientId/{clientId}")
        public ClientDAO getClientByClientId(@PathVariable String clientId) {
            return clientService.getClientByClientId(clientId);
        }
//        @GetMapping("/getClientsByName/{clientsName}")
//        public List<ClientDAO> getClientsByName(@PathVariable String clientsName) {
//            return clientService.getClientsByName(clientsName);
//        }

    }
