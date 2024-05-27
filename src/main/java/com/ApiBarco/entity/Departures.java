package com.ApiBarco.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.util.Calendar;

@Entity(name = "Departure")
@Data
public class Departures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_departure;
    private Calendar departure_time;

    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private Master master;

    public Departures(Calendar departure_time, Master master) {
        this.departure_time = departure_time;
        this.master = master;
    }

    public Departures() {
    }
}
