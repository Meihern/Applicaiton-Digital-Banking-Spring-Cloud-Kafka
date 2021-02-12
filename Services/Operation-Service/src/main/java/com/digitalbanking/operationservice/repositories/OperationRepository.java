package com.digitalbanking.operationservice.repositories;

import com.digitalbanking.operationservice.entities.Compte;
import com.digitalbanking.operationservice.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.PagedModel;

@RepositoryRestResource
public interface OperationRepository extends JpaRepository<Operation, Long>{
    Page<Operation> findOperationsByCompte(Compte compte, Pageable pageable);
}
