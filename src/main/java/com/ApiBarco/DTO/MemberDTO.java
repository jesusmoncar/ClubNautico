package com.ApiBarco.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    private boolean is_master;

    @NotNull(message = "El numero de amarre no puede ser nulo")
    private String dockNumber;

    @NotNull(message = "La cuota no puede ser nula")
    private Double fee;

    private List<Long> ship_ids;
    private List<String> ship_registrations;

    private Long permitNumber;

    public MemberDTO(long idMember, String name, String lastName, boolean master, String dockNumber, double fee, List<Long> shipIds, List<String> shipRegistrations, Long permitNumber) {
        this.id_member = idMember;
        this.name = name;
        this.last_name = lastName;
        this.is_master = master;
        this.dockNumber = dockNumber;
        this.fee = fee;
        this.ship_ids = shipIds;
        this.ship_registrations = shipRegistrations;
        this.permitNumber= permitNumber;
    }
}
