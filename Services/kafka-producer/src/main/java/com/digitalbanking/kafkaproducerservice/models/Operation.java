package com.digitalbanking.kafkaproducerservice.models;

import lombok.Data;

import java.util.Date;

@Data
public class Operation {
    private Long id;
    private Date date_operation;
    private double montant;
    private String type;
}
