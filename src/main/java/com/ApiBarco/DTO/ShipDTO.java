package com.ApiBarco.DTO;

import lombok.Data;

@Data
public class ShipDTO {
    private long id_ship;
    private String registration_tag;
    //private String model;
    // private int size;


    public ShipDTO(long id_ship, String registration_tag) {
        this.id_ship = id_ship;
        this.registration_tag = registration_tag;
    }

    public ShipDTO(){

    }

}
