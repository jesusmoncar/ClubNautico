package com.ApiBarco.Controller;

import com.ApiBarco.DTO.DepartureDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.Service.DepartureService;
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

    @PostMapping
    public ResponseEntity<Void> createDeparture(@Valid @RequestBody DepartureDTO departureDTO) throws ClubNauticoNotFoundException {
        departureService.createDeparture(departureDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartureDTO>> getAllDepartures() {
        List<DepartureDTO> departures = departureService.getAllDepartures();
        return new ResponseEntity<>(departures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartureDTO> getDepartureById(@PathVariable long id) throws ClubNauticoNotFoundException {
        DepartureDTO departuresList = departureService.getDepartureById(id);
        return new ResponseEntity<>(departuresList, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions( MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(errors);
    }
}
