package com.ApiBarco.DTO;

import lombok.Data;
import java.util.List;

@Data
public class MemberDTO {
    private long id_member;
    private String name;
    private String last_name;
    private boolean is_master;
    private List<Long> ship_ids;

    public MemberDTO(long id_member, String name, String last_name, boolean is_master, List<Long> ship_ids) {
        this.id_member = id_member;
        this.name = name;
        this.last_name = last_name;
        this.is_master = is_master;
        this.ship_ids = ship_ids;
    }

    public MemberDTO() {
    }
}
