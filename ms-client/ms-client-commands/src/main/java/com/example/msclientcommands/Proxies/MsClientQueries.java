package com.example.msclientcommands.Proxies;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ms-client-queries")
@LoadBalancerClient(name = "ms-client-queries")

public interface MsClientQueries {
}
