package com.digitalbanking.clientservice;

import com.digitalbanking.clientservice.entities.Client;
import com.digitalbanking.clientservice.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(RepositoryRestConfiguration repositoryRestConfiguration, ClientRepository clientRepository){
        repositoryRestConfiguration.exposeIdsFor(Client.class);
        return args -> {
            clientRepository.save(new Client(null,"be894720", "youssef", "achir.youssef97@gmail.com"));
            clientRepository.save(new Client(null, "bf78142", "ouiam", "ouiamkhattach@gmail.com"));
            clientRepository.save(new Client(null,"bf48112", "oussama", "oussamaaitalla@gmail.com"));
            clientRepository.save(new Client(null, "bc78204" ,"hajar", "hajarlablaoui@gmail.com"));
            clientRepository.save(new Client(null, "bg48120" ,"yasser", "yasserchihab@gmail.com"));
            clientRepository.findAll().forEach(customer -> {
                System.out.println(customer.toString());
            });
        };
    }
}
