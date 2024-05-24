package com.ApiBarco.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Ship")
@Data
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ship;
    private String registration_tag;
    private String model;
    private int size;

    public Ship(){
    }

    public Ship(int id_ship, String registration_tag, String model, int size) {
        this.id_ship = id_ship;
        this.registration_tag = registration_tag;
        this.model = model;
        this.size = size;
    }
}
