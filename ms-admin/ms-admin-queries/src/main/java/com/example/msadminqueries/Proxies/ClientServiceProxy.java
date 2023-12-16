package com.example.msadminqueries.Proxies;

import com.example.msadminqueries.DAOs.ClientDAO;
import com.example.msadminqueries.DAOs.UserKDAO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-auth")
@LoadBalancerClient(name = "ms-auth")
public interface ClientServiceProxy {

    @GetMapping("/v1/api/user/getAllUsers")
    public List<UserKDAO> getAllClients();

    @GetMapping("/v1/api/user/getUserByEmail")
    public Optional<UserKDAO> getClientByClientEmail(@RequestParam("email") String clientEmail);

    @GetMapping("/v1/api/user/getUserByID")
    public Optional<UserKDAO> getClientByClientId(@RequestParam("id") String clientEmail);

//    @GetMapping("/v1/api/user/getClientsByName/{clientsName}")
//    public List<ClientDAO> getClientsByName(@PathVariable String clientsName);

}
