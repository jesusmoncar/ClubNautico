package com.ApiBarco.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Member")
@Data
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_member;
    private String name;
    private String last_name;

    private boolean is_master;

    @OneToMany(mappedBy = "member" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ship> ships;



    public Member(long id_member, String name, String last_name, boolean is_master, List<Ship> ships) {
        this.id_member = id_member;
        this.name = name;
        this.last_name = last_name;
        this.is_master = is_master;
        this.ships = ships;
    }
}
