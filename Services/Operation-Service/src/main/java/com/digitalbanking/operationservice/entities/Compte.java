package com.digitalbanking.operationservice.entities;


import com.digitalbanking.operationservice.models.Client;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "comptes")
@Data @NoArgsConstructor @AllArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private double solde;
    private Date date_creation;
    private String type;
    private String etat;
    @OneToMany(mappedBy = "compte")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Operation> operations;
    @Transient
    private Client client;
    private Long clientId;
}
