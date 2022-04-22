package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.repository.FireStationRepository;
import com.safetynet.alerts.domain.model.FireStation;
import com.safetynet.alerts.web.dto.FireStationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    @Autowired
    FireStationRepository fireStationRepository;

    public List<FireStation> getFireStations () {
        return fireStationRepository.findAll();
    }

    public FireStation getFireStation (int station) {
        Optional<FireStation> optionalFireStation = fireStationRepository.findById(station);
        optionalFireStation.orElseThrow(NoSuchElementException::new);
        return optionalFireStation.get();
    }

    public void deleteMappingFireStation(int station) {
        fireStationRepository.deleteMappingFireStation(station);
    }

    public void deleteMappingAddress(String address) {
        fireStationRepository.deleteMappingAddress(address);
    }

    public FireStation addMappingFiresStationAddress (int station, String address) {
        return fireStationRepository.addMappingFiresStationAddress(station, address);
    }

    public FireStation updateMappingFireStationAddress(int station, String address) {
        deleteMappingAddress(address);
        return addMappingFiresStationAddress(station,address);
    }

    public FireStationDto fireStationToFireStationDto (FireStation fireStation) {
        FireStationDto fireStationDto = new FireStationDto();
        fireStationDto.setStation(fireStation.getStation());
        fireStationDto.setAdresses(fireStation.getAddresses());
        return fireStationDto;
    }

    public List<FireStationDto> listFireStationToFireStationDto (List<FireStation> listFireStation) {
        return listFireStation.stream().map(this::fireStationToFireStationDto).collect(Collectors.toList());
    }

    public List<String> listAddressesByNUmber (int station) throws NoSuchElementException {
        Optional<List<String>> optionalStrings = fireStationRepository.getListFireStations().stream()
                .filter(fireStation -> fireStation.getStation() == station)
                .map(FireStation::getAddresses)
                .findFirst();
        return optionalStrings.orElseThrow(NoSuchElementException::new);
    }

}
