package com.ApiBarco.Service;

import com.ApiBarco.DTO.DepartureDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.entity.Departures;
import com.ApiBarco.entity.Master;
import com.ApiBarco.entity.Ship;
import com.ApiBarco.repository.DepartureRepository;
import com.ApiBarco.repository.MasterRepository;
import com.ApiBarco.repository.ShipRepository;
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

    @Autowired
    private ShipRepository shipRepository;

    public DepartureDTO getDepartureById(long id) throws ClubNauticoNotFoundException {
        Optional<Departures> departureOpt = departuresRepository.findById(id);
        if (!departureOpt.isPresent()) {
            throw new ClubNauticoNotFoundException("La salida con la id " + id + " no existe");
        }
        Departures departure = departureOpt.get();
        return convertToDTO(departure);
    }

    public List<DepartureDTO> getAllDepartures() {
        List<Departures> departuresList = departuresRepository.findAll();
        return departuresList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Departures createDeparture(DepartureDTO departureDTO) throws ClubNauticoNotFoundException {
        Master master = masterRepository.findById(departureDTO.getMasterId())
                .orElseThrow(() -> new ClubNauticoNotFoundException("El patrón con la id " + departureDTO.getMasterId() + " no existe"));
        Ship ship = shipRepository.findById(departureDTO.getShipId())
                .orElseThrow(() -> new ClubNauticoNotFoundException("El barco con la id " + departureDTO.getShipId() + " no existe"));

        Departures departure = new Departures(departureDTO.getDeparture_time(), master, ship);
        return departuresRepository.save(departure);
    }

    public void deleteAllDepartures() {
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

    public DepartureDTO updateDeparture(long id, DepartureDTO departureDTO) throws ClubNauticoNotFoundException {
        Optional<Departures> departureOpt = departuresRepository.findById(id);
        if (!departureOpt.isPresent()) {
            throw new ClubNauticoNotFoundException("La salida con la id " + id + " no existe");
        }
        Departures departure = departureOpt.get();

        Master master = masterRepository.findById(departureDTO.getMasterId())
                .orElseThrow(() -> new ClubNauticoNotFoundException("El patrón con la id " + departureDTO.getMasterId() + " no existe"));
        Ship ship = shipRepository.findById(departureDTO.getShipId())
                .orElseThrow(() -> new ClubNauticoNotFoundException("El barco con la id " + departureDTO.getShipId() + " no existe"));

        departure.setDeparture_time(departureDTO.getDeparture_time());
        departure.setMaster(master);
        departure.setShip(ship);

        departuresRepository.save(departure);
        return convertToDTO(departure);
    }

    public DepartureDTO convertToDTO(Departures departure) {
        Long masterId = (departure.getMaster() != null) ? departure.getMaster().getId_master() : null;
        Long shipId = (departure.getShip() != null) ? departure.getShip().getId_ship() : null;
        return new DepartureDTO(departure.getId_departure(), departure.getDeparture_time(), masterId, shipId);
    }
}
