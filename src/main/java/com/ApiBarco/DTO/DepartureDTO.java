package com.ApiBarco.DTO;

import lombok.Data;
import java.util.Calendar;

@Data
public class DepartureDTO {
    private int id_departure;
    private Calendar departure_time;
    private int masterId;
}
