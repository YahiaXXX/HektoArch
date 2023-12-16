//package com.example.msadmincommand.Proxies;
//
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name = "ms-client-commands")
//@LoadBalancerClient(name = "ms-client-commands")
//public interface ClientProxy {
//
//    @DeleteMapping("/delete/{clientId}")
//    public void deleteClientByClientId(@PathVariable("clientId") String clientId);
//
//
//}
