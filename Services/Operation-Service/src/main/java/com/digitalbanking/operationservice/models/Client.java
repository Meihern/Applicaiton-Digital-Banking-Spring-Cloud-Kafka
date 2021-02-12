package com.digitalbanking.operationservice.models;

import lombok.Data;

@Data
public class Client {
    private Long id;
    private String code;
    private String nom;
    private String email;
}
