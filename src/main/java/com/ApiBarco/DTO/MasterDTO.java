package com.ApiBarco.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class MasterDTO {

    private Long id_master;
    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;
    @NotNull(message = "El apellido no puede ser nulo")
    @NotEmpty(message = "El apellido no puede estar vacio")
    private String last_name;
    @NotNull(message = "El permiso no puede ser nulo")

    @Max(value = 99999999, message = "El permiso no puede tener más de 8 dígitos")
    private Long permit_number;


    private List<DepartureDTO> departures;


    public MasterDTO(Long idMaster, String name, String lastName, Long permitNumber, List<DepartureDTO> departures) {
        this.id_master = idMaster;
        this.name = name;
        this.last_name = lastName;
        this.permit_number = permitNumber;
        this.departures = departures;
    }


}
