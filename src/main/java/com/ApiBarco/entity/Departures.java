package com.ApiBarco.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Departures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_departure;
    private String departure_time;

    public Departures(String departure_time, int id_departure) {
        this.departure_time = departure_time;
        this.id_departure = id_departure;
    }

    public Departures(){
    }
}
