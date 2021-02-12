package com.digitalbanking.operationservice.services;

import com.digitalbanking.operationservice.entities.Compte;
import com.digitalbanking.operationservice.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface OperationService {
    void addCompte(Compte compte);
    void verserMontant(Compte compte, double montant);
    void retraitMontant(Compte compte, double montant);
    void effectuerVirement(Compte compteSender, Compte compteReceiver, double montant);
    Page<Operation> consulterOperations(Compte compte, Pageable page);
    Compte getCompteByCode(String code);
    void activerCompte(Compte compte);
    void suspendreCompte(Compte compte);
}
