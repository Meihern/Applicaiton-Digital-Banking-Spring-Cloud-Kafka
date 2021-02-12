package com.digitalbanking.kafkadataanalytics.models;

import lombok.Data;

import java.util.Date;

@Data
public class Operation {
    private Long id;
    private String codeCompte;
    private String type;
    private double montant;
    private Date date;
}
