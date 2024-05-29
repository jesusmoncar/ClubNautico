package com.ApiBarco.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Ship")
@Data
@NoArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ship;
    private String registration_tag;
    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Departures> departures;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_member")
    private Member member;

    public Ship(Long id_ship, String registration_tag, Member member) {
        this.id_ship = id_ship;
        this.registration_tag = registration_tag;
        this.member = member;
    }
}
