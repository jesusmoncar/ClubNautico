package com.ApiBarco.entity;

import com.ApiBarco.DTO.MemberDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "Member")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_member;
    private String name;
    private String last_name;
    private boolean is_master;
    private long id_ship;

    public Member(long id_member, String name, String last_name, boolean is_master, long id_ship) {
        this.id_member = id_member;
        this.name = name;
        this.last_name = last_name;
        this.is_master = is_master;
        this.id_ship = id_ship;
    }

    public Member() {
    }

    public MemberDTO toDTO() {
        return new MemberDTO(this.id_member, this.name, this.last_name, this.is_master, this.id_ship);
    }
}
