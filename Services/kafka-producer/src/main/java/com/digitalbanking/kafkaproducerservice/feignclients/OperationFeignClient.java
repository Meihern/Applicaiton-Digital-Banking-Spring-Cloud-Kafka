package com.digitalbanking.kafkaproducerservice.feignclients;

import com.digitalbanking.kafkaproducerservice.models.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "OPERATION-SERVICE")
public interface OperationFeignClient {
    @GetMapping(path = "/operations")
    PagedModel<Operation> getOperations();
}
