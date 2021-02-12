package com.digitalbanking.operationservice.formsdata;

import lombok.Data;

@Data
public class OperationVirementForm {
    private String codeCompteSender;
    private String codeCompteReceiver;
    private double montant;
}
