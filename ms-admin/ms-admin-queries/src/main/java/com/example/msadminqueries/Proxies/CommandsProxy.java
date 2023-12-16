package com.example.msadminqueries.Proxies;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-admin-commands")
@LoadBalancerClient(name = "ms-admin-commands")
public interface CommandsProxy {

}

