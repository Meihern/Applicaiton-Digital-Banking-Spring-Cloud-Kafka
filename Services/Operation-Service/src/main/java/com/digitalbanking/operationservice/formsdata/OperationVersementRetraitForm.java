package com.digitalbanking.operationservice.formsdata;

import lombok.Data;

@Data
public class OperationVersementRetraitForm {
    private String codeCompte;
    private double montant;
}
