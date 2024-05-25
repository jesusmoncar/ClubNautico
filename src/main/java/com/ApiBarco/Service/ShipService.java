package com.ApiBarco.service;

import com.ApiBarco.DTO.ShipDTO;
import com.ApiBarco.entity.Member;
import com.ApiBarco.entity.Ship;
import com.ApiBarco.repository.MemberRepository;
import com.ApiBarco.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private MemberRepository memberRepository;

    public ShipDTO getShipById(Long id) {
        Ship ship = shipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ship not found"));
        return convertToDTO(ship);
    }

    public List<ShipDTO> getAllShips() {
        List<Ship> ships = shipRepository.findAll();
        return ships.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Ship createShip(ShipDTO shipDTO) {
        Member member = memberRepository.findById(shipDTO.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Ship ship = new Ship(shipDTO.getId_ship(), shipDTO.getRegistration_tag(), member);
        shipRepository.save(ship);
        return ship;
    }

    private ShipDTO convertToDTO(Ship ship) {
        Long memberId = (ship.getMember() != null) ? ship.getMember().getId_member() : null;
        return new ShipDTO(ship.getId_ship(), ship.getRegistration_tag(), memberId);
    }
}
