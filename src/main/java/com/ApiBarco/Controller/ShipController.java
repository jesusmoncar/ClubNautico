package com.ApiBarco.Controller;

import com.ApiBarco.DTO.ShipDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.entity.Ship;
import com.ApiBarco.Service.ShipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @GetMapping("/{id}")
    public ResponseEntity<ShipDTO> getShipById(@PathVariable("id") Long id) throws ClubNauticoNotFoundException {
        ShipDTO shipDTO = shipService.getShipById(id);
        return new ResponseEntity<>(shipDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ShipDTO>> getAllShips() {
        List<ShipDTO> shipDTOs = shipService.getAllShips();
        return new ResponseEntity<>(shipDTOs, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAllShips() {
        shipService.deleteAllShip();
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<ShipDTO> createShip(@Valid @RequestBody ShipDTO shipDTO) throws ClubNauticoNotFoundException{
        Ship ship = shipService.createShip(shipDTO);
        return new ResponseEntity<>(shipDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipById(@PathVariable long id) throws ClubNauticoNotFoundException {
        shipService.deleteShipById(id);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(errors);
    }


}
