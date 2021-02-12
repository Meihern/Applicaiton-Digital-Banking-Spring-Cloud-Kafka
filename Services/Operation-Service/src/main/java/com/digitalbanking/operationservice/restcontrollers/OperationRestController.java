package com.digitalbanking.operationservice.restcontrollers;

import com.digitalbanking.operationservice.entities.Compte;
import com.digitalbanking.operationservice.entities.Operation;
import com.digitalbanking.operationservice.feignclients.ClientFeignClient;
import com.digitalbanking.operationservice.formsdata.CompteForm;
import com.digitalbanking.operationservice.formsdata.OperationVersementRetraitForm;
import com.digitalbanking.operationservice.formsdata.OperationVirementForm;
import com.digitalbanking.operationservice.repositories.CompteRepository;
import com.digitalbanking.operationservice.repositories.OperationRepository;
import com.digitalbanking.operationservice.services.OperationService;
import com.mifmif.common.regex.Generex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class OperationRestController {
    private final CompteRepository compteRepository;
    private final OperationService operationService;

    public OperationRestController(ClientFeignClient clientFeignClient, OperationRepository operationRepository, CompteRepository compteRepository, OperationService operationService) {
        this.compteRepository = compteRepository;
        this.operationService = operationService;
    }

    @PostMapping("/addCompte")
    public void addCompte(@RequestBody CompteForm compteForm){
        Generex generex = new Generex("[0-9]{27}");
        Compte compte = new Compte();
        compte.setSolde(compteForm.getSolde());
        compte.setClientId(compteForm.getClientId());
        compte.setType(compteForm.getType());
        compte.setDate_creation(new Date());
        compte.setCode(generex.random());
        operationService.addCompte(compte);
    }

    @PostMapping("/versement")
    public void versement(@RequestBody OperationVersementRetraitForm operationVersementRetraitForm){
        Compte compte = compteRepository.findCompteByCode(operationVersementRetraitForm.getCodeCompte());
        operationService.verserMontant(compte, operationVersementRetraitForm.getMontant());
    }

    @PostMapping("/retrait")
    public void retrait(@RequestBody OperationVersementRetraitForm operationVersementRetraitForm){
        Compte compte = compteRepository.findCompteByCode(operationVersementRetraitForm.getCodeCompte());
        operationService.retraitMontant(compte, operationVersementRetraitForm.getMontant());
    }

    @PostMapping("/virement")
    public void virement(@RequestBody OperationVirementForm operationVirementForm){
        Compte compteSender = compteRepository.findCompteByCode(operationVirementForm.getCodeCompteSender());
        Compte compteReceiver = compteRepository.findCompteByCode(operationVirementForm.getCodeCompteReceiver());
        operationService.effectuerVirement(compteSender, compteReceiver, operationVirementForm.getMontant());
    }

    @GetMapping("/consulterOperations/{code}")
    public Page<Operation> consulterOperations(@PathVariable String code, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page, size);
        return operationService.consulterOperations(compteRepository.findCompteByCode(code), pageable);
    }

    @GetMapping("/fullCompte/{code}")
    public Compte getFullCompte(@PathVariable String code){
        return operationService.getCompteByCode(code);
    }

    @GetMapping("/activerCompte/{code}")
    public void actvierCompte(@PathVariable String code){
        operationService.activerCompte(compteRepository.findCompteByCode(code));
    }

    @GetMapping("/suspendreCompte/{code}")
    public void suspendreCompte(@PathVariable String code){
        operationService.suspendreCompte(compteRepository.findCompteByCode(code));
    }

}
