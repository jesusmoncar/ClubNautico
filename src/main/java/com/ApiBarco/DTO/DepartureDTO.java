package com.ApiBarco.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartureDTO {
    private Long id_departure;


    @NotNull(message = "La fecha no puede estar vacia")
    private Calendar departure_time;
    @NotNull(message = "El id del patron es obligatorio")
    private Long masterId;
    @NotNull(message = "El id del barco es obligatorio")
    private Long shipId;

}
