package com.ApiBarco.Controller;

import com.ApiBarco.DTO.DepartureDTO;
import com.ApiBarco.DTO.MasterDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.Service.MasterService;
import com.ApiBarco.entity.Departures;
import com.ApiBarco.entity.Master;
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
@RequestMapping("/api/masters")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @GetMapping("/{id}")
    public ResponseEntity<MasterDTO> getMasterById(@PathVariable long id) throws ClubNauticoNotFoundException {
        MasterDTO masterDTO = masterService.getMasterById(id);
        return new ResponseEntity<>(masterDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MasterDTO>> getAllMasters() throws ClubNauticoNotFoundException {
        List<MasterDTO> masters = masterService.getAllMasters();
        if (masters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(masters, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MasterDTO> createMaster(@Valid @RequestBody MasterDTO masterDTO) throws ClubNauticoNotFoundException {
        Master master1 = masterService.createMaster(masterDTO);
        MasterDTO masterDTO1 = masterService.convertToDTO(master1);

        return new ResponseEntity<>(masterDTO1,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterDTO> updateMaster(@PathVariable long id, @Valid @RequestBody MasterDTO masterDTO) throws ClubNauticoNotFoundException {
        MasterDTO updatedMasterDTO = masterService.updateMaster(id, masterDTO);
        return new ResponseEntity<>(updatedMasterDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMasters() {
        masterService.deleteAllMasters();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasterById(@PathVariable long id) throws ClubNauticoNotFoundException {
        masterService.deleteMasterById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(errors);
    }
}
