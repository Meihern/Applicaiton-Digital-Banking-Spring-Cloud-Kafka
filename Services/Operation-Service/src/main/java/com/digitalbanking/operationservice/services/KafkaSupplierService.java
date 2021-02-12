package com.digitalbanking.operationservice.services;

import com.digitalbanking.operationservice.entities.Operation;
import com.digitalbanking.operationservice.models.OperationKafkaModel;
import com.digitalbanking.operationservice.repositories.CompteRepository;
import com.mifmif.common.regex.Generex;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

@Service
public class KafkaSupplierService {
    private final CompteRepository compteRepository;

    public KafkaSupplierService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Bean
    public Supplier<OperationKafkaModel> operationSupplier(){
        return () -> {
            Generex generex_code_compte = new Generex("375823464757455212665525036|251279689081516418838654138|450144203059253212618897493|772338858361045228015640386}");
            Generex generex_type_operation = new Generex("DEBIT|CREDIT");
            return new OperationKafkaModel(
                    generex_code_compte.random(),
                    generex_type_operation.random(),
                    Math.random()*100,
                    new Date()
            );
        };
        }
}

