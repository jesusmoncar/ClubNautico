package com.ApiBarco.DTO;

import lombok.Data;

@Data
public class ShipDTO {
    private Long id_ship;
    private String registration_tag;
    private Long memberId;

    public ShipDTO(Long id_ship, String registration_tag, Long memberId) {
        this.id_ship = id_ship;
        this.registration_tag = registration_tag;
        this.memberId = memberId;
    }


}
