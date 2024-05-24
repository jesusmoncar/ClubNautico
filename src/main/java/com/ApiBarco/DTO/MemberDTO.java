package com.ApiBarco.DTO;

import lombok.Data;

@Data
public class MemberDTO {
    private long id_member;
    private String name;
    private String last_name;
    private boolean is_master;
    private long id_ship;

    public MemberDTO(long id_member, String name, String last_name, boolean is_master, long id_ship) {
        this.id_member = id_member;
        this.name = name;
        this.last_name = last_name;
        this.is_master = is_master;
        this.id_ship = id_ship;
    }

    public MemberDTO() {
    }
}
