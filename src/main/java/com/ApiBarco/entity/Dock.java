package com.ApiBarco.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Dock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_dock;
    private int number;

    public Dock(){

    }

    public Dock(int id_dock, int number) {
        this.id_dock = id_dock;
        this.number = number;
    }
}
