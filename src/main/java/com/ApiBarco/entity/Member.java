package com.ApiBarco.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;
import com.ApiBarco.entity.*;
@Component
@Entity(name = "Member")
@Data
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_member;
    private String name;
    private String last_name;
    private boolean is_master;

    private Ship ej;
    @OneToMany
    @JoinColumn(name="id_ship")
    private Ship ship;

    public Member(int id_member, String name, String last_name, boolean is_master, Ship ship) {
    }

    public Member(int id_member, String name, String last_name, boolean is_master){
        this.id_member = id_member;
        this.name = name;
        this.last_name = last_name;
        this.is_master = is_master;
    }

    public Member(){

    }






}
