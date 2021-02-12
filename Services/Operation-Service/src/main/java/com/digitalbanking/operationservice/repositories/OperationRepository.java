package com.digitalbanking.operationservice.repositories;

import com.digitalbanking.operationservice.entities.Compte;
import com.digitalbanking.operationservice.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.PagedModel;

@RepositoryRestResource
public interface OperationRepository extends JpaRepository<Operation, Long>{
    PagedModel<Operation> findOperationsByCompte(Compte compte);
}
