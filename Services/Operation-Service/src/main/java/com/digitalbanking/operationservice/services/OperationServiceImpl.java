package com.digitalbanking.operationservice.services;

import com.digitalbanking.operationservice.entities.Compte;
import com.digitalbanking.operationservice.entities.Operation;
import com.digitalbanking.operationservice.feignclients.ClientFeignClient;
import com.digitalbanking.operationservice.repositories.CompteRepository;
import com.digitalbanking.operationservice.repositories.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class OperationServiceImpl implements OperationService{
    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;
    private final ClientFeignClient clientFeignClient;

    public OperationServiceImpl(CompteRepository compteRepository, OperationRepository operationRepository, ClientFeignClient clientFeignClient) {
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
        this.clientFeignClient = clientFeignClient;
    }

    @Override
    public void addCompte(Compte compte) {
        compteRepository.save(compte);
    }

    @Override
    public void verserMontant(Compte compte, double montant) {
        compte.setSolde(compte.getSolde()+montant);
        operationRepository.save(new Operation(null,new Date(), montant, "DEBIT", compte));
        compteRepository.save(compte);
    }

    @Override
    public void retraitMontant(Compte compte, double montant) {
        compte.setSolde(compte.getSolde()-montant);
        operationRepository.save(new Operation(null,new Date(), montant, "CREDIT", compte));
        compteRepository.save(compte);
    }

    @Override
    public void effectuerVirement(Compte compteSender, Compte compteReceiver, double montant) {
        if(compteSender.getSolde() <= montant)
            return;
        compteSender.setSolde(compteSender.getSolde()-montant);
        compteReceiver.setSolde(compteReceiver.getSolde()+montant);
        operationRepository.save(new Operation(null, new Date(), montant, "CREDIT", compteSender));
        operationRepository.save(new Operation(null, new Date(), montant, "DEBIT", compteReceiver));
        compteRepository.save(compteSender);
        compteRepository.save(compteReceiver);
    }

    @Override
    public Page<Operation> consulterOperations(Compte compte, Pageable page) {
        return operationRepository.findOperationsByCompte(compte, page);
    }

    @Override
    public Compte getCompteByCode(String code) {
        Compte compte = compteRepository.findCompteByCode(code);
        compte.setClient(clientFeignClient.getClientById(compte.getClientId()));
        return compte;
    }

    @Override
    public void activerCompte(Compte compte) {
        compte.setEtat("ACTIVE");
        compteRepository.save(compte);
    }

    @Override
    public void suspendreCompte(Compte compte) {
        compte.setEtat("SUSPENDED");
        compteRepository.save(compte);
    }
}
