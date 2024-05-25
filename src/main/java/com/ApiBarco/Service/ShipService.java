//package com.ApiBarco.service;
//
//import com.ApiBarco.DTO.ShipDTO;
//import com.ApiBarco.entity.Member;
//import com.ApiBarco.repository.ShipRepository;
//import com.ApiBarco.entity.Ship;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class ShipService {
//
//    @Autowired
//    private ShipRepository shipRepository;
//
//    public ShipDTO getShipById( long id){
//        Ship ship = shipRepository.findById(id).orElseThrow(() -> new RuntimeException("Ship not found"));
//        return convertToDTO(ship);
//    }
//
//    public List<ShipDTO> getAllShip() {
//        List<Ship> ships = shipRepository.findAll();
//        return ships.stream().map(this::convertToDTO).collect(Collectors.toList());
//    }
//
//    public void createShip( ShipDTO shipDTO) {
//        Ship  ship = new Member(shipDTO.getid_ship(), shipDTO.registration_tag());
//        shipRepository.save(shipDTO);
//    }
//
//    private ShipDTO convertToDTO(Ship ship){
//        return new ShipDTO(ship.getId_ship(), ship.getRegistration_tag());
//    }
//}
