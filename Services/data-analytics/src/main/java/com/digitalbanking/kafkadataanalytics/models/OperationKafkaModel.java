package com.digitalbanking.kafkadataanalytics.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class OperationKafkaModel {
    private String codeCompte;
    private String type;
    private double montant;
    private Date date;
}
