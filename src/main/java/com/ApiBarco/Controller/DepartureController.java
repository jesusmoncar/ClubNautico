package com.ApiBarco.Controller;

import com.ApiBarco.DTO.DepartureDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.Service.DepartureService;
import com.ApiBarco.entity.Departures;
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
@RequestMapping("/api/departures")
public class DepartureController {

    @Autowired
    private DepartureService departureService;

    @GetMapping("/{id}")
    public ResponseEntity<DepartureDTO> getDepartureById(@PathVariable long id) throws ClubNauticoNotFoundException {
        DepartureDTO departureDTO = departureService.getDepartureById(id);
        return new ResponseEntity<>(departureDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartureDTO>> getAllDepartures() throws ClubNauticoNotFoundException {
        List<DepartureDTO> departures = departureService.getAllDepartures();
        if (departures.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departures, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartureDTO> createDeparture(@Valid @RequestBody DepartureDTO departureDTO) throws ClubNauticoNotFoundException {
        Departures departure = departureService.createDeparture(departureDTO);
        DepartureDTO createdDepartureDTO = departureService.convertToDTO(departure);
        return new ResponseEntity<>(createdDepartureDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartureDTO> updateDeparture(@PathVariable long id, @Valid @RequestBody DepartureDTO departureDTO) throws ClubNauticoNotFoundException {
        DepartureDTO updatedDepartureDTO = departureService.updateDeparture(id, departureDTO);
        return new ResponseEntity<>(updatedDepartureDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllDepartures() {
        departureService.deleteAllDepartures();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartureById(@PathVariable long id) throws ClubNauticoNotFoundException {
        departureService.deleteDepartureById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(errors);
    }
}
