package com.ApiBarco.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_master;
    private String name;
    private String last_name;
    private int permit_number;

    public Master(Long id_master, String name, String last_name, int permit_number) {
        this.id_master = id_master;
        this.name = name;
        this.last_name = last_name;
        this.permit_number = permit_number;
    }

    public Master() {
    }
}
