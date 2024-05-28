package com.ApiBarco.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Entity(name = "Departure")
@Data
public class Departures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_departure;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar departure_time;

    @ManyToOne
    @JoinColumn(name = "id_master", nullable = false)
    private Master master;

    @ManyToOne
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;

    public Departures(Calendar departure_time, Master master, Ship ship) {
        this.departure_time = departure_time;
        this.master = master;
        this.ship = ship;
    }

    public Departures() {
    }
}
