package com.ApiBarco.Service;


import com.ApiBarco.DTO.MasterDTO;
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
public class MasterService {

    @Autowired
    private MasterRepository masterRepository;

    @Autowired
    private DepartureRepository departureRepository;

    public MasterDTO getMasterById(long id) throws ClubNauticoNotFoundException{
        Optional<Master> masterOpt = masterRepository.findById(id);
        if(!masterOpt.isPresent()){
            throw new ClubNauticoNotFoundException("El patron con la id " + id + " no existe");
        }
        Master master = masterOpt.get();
        return convertToDTO(master);
    }

    public List<MasterDTO> getAllMembers() throws ClubNauticoNotFoundException{
        List<Master> masters = masterRepository.findAll();
        return masters.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public Master createMaster(MasterDTO masterDTO) throws ClubNauticoNotFoundException {
        List<Departures> departures = masterDTO.getDepartureIds().stream()
                .map(departureId -> {
                    try {
                        return departureRepository.findById(departureId)
                                .orElseThrow(() -> new ClubNauticoNotFoundException("La salida con la id " + departureId + " no existe"));
                    } catch (ClubNauticoNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        Master master = new Master((long) masterDTO.getId_master(), masterDTO.getName(), masterDTO.getLast_name(), masterDTO.getPermit_number());
        departures.forEach(departure -> departure.setMaster(master));

        return masterRepository.save(master);
    }



    public void deleteAllMasters(){
        masterRepository.deleteAll();
    }

    public void deleteMasterById(long masterId) throws ClubNauticoNotFoundException{
        Optional<Master> masterOpt = masterRepository.findById(masterId);
        if(!masterOpt.isPresent()){
            throw new ClubNauticoNotFoundException("El patron con la id " + masterId + " no existe");
        }
        masterRepository.deleteById(masterId);
    }

    private MasterDTO convertToDTO(Master master){
        return new MasterDTO(master.getId_master(), master.getName(), master.getLast_name(), master.getPermit_number(), departureIds);
    }

}
