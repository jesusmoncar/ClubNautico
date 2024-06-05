package com.ApiBarco.Service;

import com.ApiBarco.DTO.DepartureDTO;
import com.ApiBarco.DTO.MasterDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.entity.Departures;
import com.ApiBarco.entity.Master;
import com.ApiBarco.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterService {

    @Autowired
    private MasterRepository masterRepository;

    public MasterDTO getMasterById(long id) throws ClubNauticoNotFoundException {
        Optional<Master> masterOpt = masterRepository.findById(id);
        if (!masterOpt.isPresent()) {
            throw new ClubNauticoNotFoundException("El patrón con la id " + id + " no existe");
        }
        Master master = masterOpt.get();
        return convertToDTO(master);
    }

    public List<MasterDTO> getAllMasters() {
        List<Master> masters = masterRepository.findAll();
        return masters.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Master createMaster(MasterDTO masterDTO) {
        Master master = new Master(masterDTO.getId_master(), masterDTO.getName(), masterDTO.getLast_name(), masterDTO.getPermit_number());
        return masterRepository.save(master);
    }

    public void deleteAllMasters() {
        masterRepository.deleteAll();
    }

    public void deleteMasterById(long masterId) throws ClubNauticoNotFoundException {
        Optional<Master> masterOpt = masterRepository.findById(masterId);
        if (!masterOpt.isPresent()) {
            throw new ClubNauticoNotFoundException("El patrón con la id " + masterId + " no existe");
        }
        masterRepository.deleteById(masterId);
    }

    public MasterDTO updateMaster(long id, MasterDTO masterDTO) throws ClubNauticoNotFoundException {
        Optional<Master> masterOpt = masterRepository.findById(id);
        if (!masterOpt.isPresent()) {
            throw new ClubNauticoNotFoundException("El patrón con la id " + id + " no existe");
        }
        Master master = masterOpt.get();
        master.setName(masterDTO.getName());
        master.setLast_name(masterDTO.getLast_name());
        master.setPermit_number(masterDTO.getPermit_number());

        masterRepository.save(master);
        return convertToDTO(master);
    }

    public MasterDTO convertToDTO(Master master) {
        List<DepartureDTO> departures = (master.getDepartures() != null) ? master.getDepartures().stream()
                .map(this::convertDepartureToDTO)
                .collect(Collectors.toList()) : new ArrayList<>();
        return new MasterDTO(master.getId_master(), master.getName(), master.getLast_name(), master.getPermit_number(), departures);
    }

    private DepartureDTO convertDepartureToDTO(Departures departure) {
        Long masterId = (departure.getMaster() != null) ? departure.getMaster().getId_master() : null;
        Long shipId = (departure.getShip() != null) ? departure.getShip().getId_ship() : null;
        return new DepartureDTO(departure.getId_departure(), departure.getDeparture_time(), masterId, shipId);
    }
}
