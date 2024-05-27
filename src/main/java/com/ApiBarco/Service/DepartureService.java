package com.ApiBarco.Service;

import com.ApiBarco.DTO.DepartureDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.entity.Departures;
import com.ApiBarco.entity.Master;
import com.ApiBarco.repository.DepartureRepository;
import com.ApiBarco.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartureService {
    @Autowired
    private DepartureRepository departuresRepository;

    @Autowired
    private MasterRepository masterRepository;


    public DepartureDTO getDepartureById(long id) throws ClubNauticoNotFoundException {
        Optional<Departures> departureOpt = departuresRepository.findById(id);
        if (!departureOpt.isPresent()) {
            throw new ClubNauticoNotFoundException("La salida con la id " + id + " no existe");
        }
        Departures departure = departureOpt.get();
        return convertToDTO(departure);
    }
    public List<DepartureDTO> getAllDepartures() throws ClubNauticoNotFoundException {
        List<Departures> departuresList = departuresRepository.findAll();
        return departuresList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Departures createDeparture(DepartureDTO departureDTO) throws ClubNauticoNotFoundException {
        Master master = masterRepository.findById((long) departureDTO.getMasterId())
                .orElseThrow(() -> new ClubNauticoNotFoundException("El patrón con la id " + departureDTO.getMasterId() + " no existe"));

        Departures departure = new Departures(departureDTO.getDeparture_time(), master);
        System.out.println(departureDTO.getDeparture_time());
        return departuresRepository.save(departure);
    }

    public void DeleteAllDepartures() {
        departuresRepository.deleteAll();
    }

    public void deleteDepartureById(long id) throws ClubNauticoNotFoundException {
        Optional<Departures> departureOpt = departuresRepository.findById(id);
        if (!departureOpt.isPresent()) {
            throw new ClubNauticoNotFoundException("La salida con la id " + id + " no existe");

        }
        Departures departure = departureOpt.get();
        departuresRepository.delete(departure);
    }


    private DepartureDTO convertToDTO(Departures departure) {
        Long masterId = (departure.getMaster() != null) ? departure.getMaster().getId_master() : null;
        return new DepartureDTO(departure.getId_departure(), departure.getDeparture_time(), masterId);
    }



}
