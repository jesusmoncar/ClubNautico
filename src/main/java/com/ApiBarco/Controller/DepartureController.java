package com.ApiBarco.Controller;

import com.ApiBarco.DTO.DepartureDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.Service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departures")
public class DepartureController {

    @Autowired
    private DepartureService departureService;

    @PostMapping
    public ResponseEntity<Void> createDepartrue( @RequestBody DepartureDTO departureDTO ) throws ClubNauticoNotFoundException {
        departureService.createDeparture(departureDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartureDTO>> getAllDepartures() throws ClubNauticoNotFoundException {

        List<DepartureDTO> departures= departureService.getAllDepartures();
        return new ResponseEntity<>(departures, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartureDTO> getMemberById(@PathVariable long id) throws ClubNauticoNotFoundException{
        DepartureDTO departuresList = departureService.getDepartureById(id);
        return new ResponseEntity<>(departuresList, HttpStatus.OK );
    }


}
