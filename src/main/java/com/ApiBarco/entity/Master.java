package com.ApiBarco.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_master;
    private String name;
    private String last_name;
    private Long permit_number;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    private List<Departures> departures;

    public Master( Long id_master, String name, String last_name, @NotNull(message = "El permiso no puede ser nulo") Long permit_number) {
        this.id_master = id_master;
        this.name = name;
        this.last_name = last_name;
        this.permit_number = permit_number;
    }

    public Master() {
    }
}
