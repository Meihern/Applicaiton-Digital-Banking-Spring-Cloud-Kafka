package com.digitalbanking.operationservice;

import com.digitalbanking.operationservice.entities.Compte;
import com.digitalbanking.operationservice.entities.Operation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableFeignClients
public class OperationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
          repositoryRestConfiguration.exposeIdsFor(Operation.class, Compte.class);
        };
    }

}
