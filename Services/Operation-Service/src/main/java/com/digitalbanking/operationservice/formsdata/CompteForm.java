package com.digitalbanking.operationservice.formsdata;

import lombok.Data;

@Data
public class CompteForm {
    private Long clientId;
    private String type;
    private double solde;
}
