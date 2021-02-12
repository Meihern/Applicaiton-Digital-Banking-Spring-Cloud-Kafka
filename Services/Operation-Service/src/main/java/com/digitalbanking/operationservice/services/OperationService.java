package com.digitalbanking.operationservice.services;

import com.digitalbanking.operationservice.entities.Compte;
import com.digitalbanking.operationservice.entities.Operation;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface OperationService {
    void addCompte(Compte compte);
    void verserMontant(Compte compte, double montant);
    void retraitMontant(Compte compte, double montant);
    void effectuerVirement(Compte compteSender, Compte compteReceiver, double montant);
    PagedModel<Operation> consulterOperations(Compte compte, int page, int size);
    Compte getCompteByCode(String code);
    void activerCompte(Compte compte);
    void suspendreCompte(Compte compte);
}
