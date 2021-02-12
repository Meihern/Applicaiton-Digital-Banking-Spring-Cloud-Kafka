package com.digitalbanking.kafkaproducerservice.restcontrollers;

import com.digitalbanking.kafkaproducerservice.feignclients.OperationFeignClient;
import com.digitalbanking.kafkaproducerservice.models.Operation;
import org.springframework.hateoas.PagedModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    private final KafkaTemplate<Integer, Operation> kafkaTemplate;
    private final OperationFeignClient operationFeignClient;

    int counter = 0;

    public KafkaController(KafkaTemplate<Integer, Operation> kafkaTemplate, OperationFeignClient operationFeignClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.operationFeignClient = operationFeignClient;
    }


    @GetMapping(path = "/publish/{topic}")
    public String send(@PathVariable String topic){
        PagedModel<Operation> operations = operationFeignClient.getOperations();
        operations.forEach(operation -> {
            ++counter;
            System.out.println(operation);
            kafkaTemplate.send(topic, counter, operation);
        });
        return operations.toString();
    }
}
