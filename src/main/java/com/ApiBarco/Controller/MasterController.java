package com.ApiBarco.Controller;

import com.ApiBarco.DTO.MasterDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.Service.MasterService;
import com.ApiBarco.entity.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masters")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @GetMapping("/{id}")
    public ResponseEntity<MasterDTO> getMasterById(@PathVariable long id) {
        try {
            MasterDTO masterDTO = masterService.getMasterById(id);
            return new ResponseEntity<>(masterDTO, HttpStatus.OK);
        } catch (ClubNauticoNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MasterDTO>> getAllMasters() {
        List<MasterDTO> masterDTOs = masterService.getAllMasters();
        return new ResponseEntity<>(masterDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MasterDTO> createMaster(@RequestBody MasterDTO masterDTO) {
        Master master = masterService.createMaster(masterDTO);
        MasterDTO createdMasterDTO = masterService.convertToDTO(master);
        return new ResponseEntity<>(createdMasterDTO, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMasters() {
        masterService.deleteAllMasters();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasterById(@PathVariable long id) {
        try {
            masterService.deleteMasterById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ClubNauticoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
