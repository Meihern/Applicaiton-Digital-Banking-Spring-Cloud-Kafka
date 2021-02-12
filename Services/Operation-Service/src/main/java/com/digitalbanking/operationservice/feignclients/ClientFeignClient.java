package com.digitalbanking.operationservice.feignclients;

import com.digitalbanking.operationservice.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientFeignClient {
    @GetMapping(path = "/clients/{id}")
    Client getClientById(@PathVariable(name = "id") Long id);
}
