package com.ApiBarco.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShipDTO {
    private Long id_ship;

    @NotNull(message = "La matricula no puede ser nula")
    @NotEmpty(message = "La matricula puede estar vacia")
    private String registration_tag;
    private Long memberId;

    public ShipDTO(Long id_ship, String registration_tag, Long memberId) {
        this.id_ship = id_ship;
        this.registration_tag = registration_tag;
        this.memberId = memberId;
    }


}
