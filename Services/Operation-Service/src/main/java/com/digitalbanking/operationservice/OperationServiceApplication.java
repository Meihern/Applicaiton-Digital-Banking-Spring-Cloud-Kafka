package com.digitalbanking.operationservice;

import com.digitalbanking.operationservice.entities.Compte;
import com.digitalbanking.operationservice.entities.Operation;
import com.digitalbanking.operationservice.repositories.CompteRepository;
import com.mifmif.common.regex.Generex;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OperationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(RepositoryRestConfiguration repositoryRestConfiguration, CompteRepository compteRepository){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Operation.class, Compte.class);

        };
    }

}
