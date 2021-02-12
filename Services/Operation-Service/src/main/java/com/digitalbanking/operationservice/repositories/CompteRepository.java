package com.digitalbanking.operationservice.repositories;

import com.digitalbanking.operationservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompteRepository extends JpaRepository<Compte, Long> {
    Compte findCompteByCode(String code);
}
