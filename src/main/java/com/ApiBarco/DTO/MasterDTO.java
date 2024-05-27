package com.ApiBarco.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MasterDTO {

    private int id_master;
    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;
    @NotNull(message = "El apellido no puede ser nulo")
    @NotEmpty(message = "El apellido no puede estar vacio")
    private String last_name;

    private int permit_number;
    private List<Long>departureIds;

    public MasterDTO(int idMaster, String name, String lastName, int permitNumber, List<Long> departureIds) {
        this.id_master = idMaster;
        this.name = name;
        this.last_name = lastName;
        this.permit_number = permitNumber;
        this.departureIds = departureIds;
    }

    public MasterDTO(){}
}
