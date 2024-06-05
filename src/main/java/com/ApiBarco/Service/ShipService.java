package com.ApiBarco.Service;

import com.ApiBarco.DTO.MasterDTO;
import com.ApiBarco.DTO.ShipDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.entity.Master;
import com.ApiBarco.entity.Member;
import com.ApiBarco.entity.Ship;
import com.ApiBarco.repository.MemberRepository;
import com.ApiBarco.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private MemberRepository memberRepository;

    public ShipDTO getShipById(Long id) throws ClubNauticoNotFoundException {
        Optional<Ship> ship = shipRepository.findById(id);
        if (!ship.isPresent()) {
            throw new ClubNauticoNotFoundException("El barco con la id " + id + " no existe");
        }
        Ship ship1 = ship.get();
        return convertToDTO(ship1);
    }

    public List<ShipDTO> getAllShips() {
        List<Ship> ships = shipRepository.findAll();
        return ships.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Ship createShip(ShipDTO shipDTO) throws ClubNauticoNotFoundException{
        Member member = memberRepository.findById(shipDTO.getMemberId())
                .orElseThrow(() -> new ClubNauticoNotFoundException("Miembro no encontrado"));
        Ship ship = new Ship(shipDTO.getId_ship(), shipDTO.getRegistration_tag(), member);
        shipRepository.save(ship);
        return ship;
    }

    private ShipDTO convertToDTO(Ship ship) {
        Long memberId = (ship.getMember() != null) ? ship.getMember().getId_member() : null;
        return new ShipDTO(ship.getId_ship(), ship.getRegistration_tag(), memberId);
    }

    public ShipDTO updateShip(long id, ShipDTO shipDTO) throws ClubNauticoNotFoundException {
        Optional<Ship> shipOpt = shipRepository.findById(id);
        if (!shipOpt.isPresent()) {
            throw new ClubNauticoNotFoundException("El barco con la id " + id + " no existe");
        }
        Ship ship = shipOpt.get();
        ship.setRegistration_tag(shipDTO.getRegistration_tag());
        shipRepository.save(ship);
        return convertToDTO(ship);
    }

    public void deleteAllShip() {
        shipRepository.deleteAll();
    }

    public void deleteShipById(long shipId) throws ClubNauticoNotFoundException {
        Optional<Ship> shipOp = shipRepository.findById(shipId);
        if (!shipOp.isPresent()) {
            throw new ClubNauticoNotFoundException("El barco con la id " + shipId + " no existe");
        }
        shipRepository.deleteById(shipId);
    }

}
