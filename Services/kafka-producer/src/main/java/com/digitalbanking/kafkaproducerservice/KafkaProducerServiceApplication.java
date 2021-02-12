package com.digitalbanking.kafkaproducerservice;

import com.digitalbanking.kafkaproducerservice.restcontrollers.KafkaController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableFeignClients
public class KafkaProducerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(KafkaController kafkaController){
        return args -> {
            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
                String topic = "OPERATIONS";
                kafkaController.send(topic);
            }, 1, 1, TimeUnit.SECONDS);
        };
    }

}
