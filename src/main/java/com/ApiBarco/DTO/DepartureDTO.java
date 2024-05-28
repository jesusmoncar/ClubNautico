package com.ApiBarco.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartureDTO {
    private int id_departure;
    @NotEmpty(message = "La fecha no puede estar vacía")
    private Calendar departure_time;
    private Long masterId;
    private Long shipId;

    public DepartureDTO ( int idDeparture , Calendar departureTime , Long masterId ) {
    }
}
