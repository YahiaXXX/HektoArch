package com.example.msclientcommands.Proxies;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ms-auth")
@LoadBalancerClient(name = "ms-auth")
public interface MsAuthProxy {

    @GetMapping("/v1/api/user/getcurrentUser")
    public ResponseEntity<Object> getcurrentUser(@CookieValue(value = "token", defaultValue = "No token found in cookie") String token);
}
