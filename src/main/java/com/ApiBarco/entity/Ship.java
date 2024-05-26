package com.ApiBarco.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Ship")
@Data
@NoArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ship;
    private String registration_tag;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_member")
    private Member member;

    public Ship(Long id_ship, String registration_tag, Member member) {
        this.id_ship = id_ship;
        this.registration_tag = registration_tag;
        this.member = member;
    }
}
