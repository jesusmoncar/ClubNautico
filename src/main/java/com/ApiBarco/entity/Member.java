package com.ApiBarco.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLInsert;


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


    private String dockNumber;
    private double fee;

    private Long permitNumber;

    @OneToMany(mappedBy = "member" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ship> ships;
    public Member(List<Ship> ships, double fee, String dockNumber, boolean is_master, String last_name, String name, long id_member) {
        this.ships = ships;
        this.fee = fee;
        this.dockNumber = dockNumber;
        this.is_master = is_master;
        this.last_name = last_name;
        this.name = name;
        this.id_member = id_member;
    }

    public Member(long idMember, String name, String lastName, boolean master, String dockNumber, Double fee, List<Ship> ships, Long permitNumber) {
        this.id_member = idMember;
        this.name = name;
        this.last_name = lastName;
        this.is_master = master;
        this.dockNumber = dockNumber;
        this.fee = fee;
        this.ships = ships;
        this.permitNumber= permitNumber;


    }
}
