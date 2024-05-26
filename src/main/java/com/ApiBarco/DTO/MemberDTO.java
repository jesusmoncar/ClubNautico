package com.ApiBarco.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.List;

@Data
public class MemberDTO {
    private long id_member;

    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;

    @NotNull(message = "El apellido no puede ser nulo")
    @NotEmpty(message = "El apellido no puede estar vacio")
    private String last_name;

    @JsonProperty("is_master")
    private boolean is_master;


    private List<Long> ship_ids;

    private List<String> ship_registrations;

    public MemberDTO(long id_member, String name, String last_name, boolean is_master, List<Long> ship_ids, List<String> ship_registrations) {
        this.id_member = id_member;
        this.name = name;
        this.last_name = last_name;
        this.is_master = is_master;
        this.ship_ids = ship_ids;
        this.ship_registrations = ship_registrations;
    }

    public MemberDTO() {
    }
}
