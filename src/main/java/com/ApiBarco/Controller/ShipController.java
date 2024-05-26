package com.ApiBarco.Controller;

import com.ApiBarco.DTO.ShipDTO;
import com.ApiBarco.entity.Ship;
import com.ApiBarco.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @GetMapping("/{id}")
    public ResponseEntity<ShipDTO> getShipById(@PathVariable("id") Long id) {
        ShipDTO shipDTO = shipService.getShipById(id);
        return new ResponseEntity<>(shipDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ShipDTO>> getAllShips() {
        List<ShipDTO> shipDTOs = shipService.getAllShips();
        return new ResponseEntity<>(shipDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ship> createShip(@RequestBody ShipDTO shipDTO) {
        Ship ship = shipService.createShip(shipDTO);
        return new ResponseEntity<>(ship, HttpStatus.CREATED);
    }
}
