package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.web.dto.FireDto;
import com.safetynet.alerts.web.dto.ListFireDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FireService {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FireStationService fireStationService;

    public ListFireDto fire (String address) throws NoSuchElementException {
        int station;
        ListFireDto listFireDto = new ListFireDto();
        station = fireStationService.fireStationByAddress(address);
        listFireDto.setStation(station);
        List<FireDto> inhabitantsList = personRepository.getListPersons().stream()
                .filter(person -> Objects.equals(person.getAddress(), address))
                .map(FireDto::new)
                .collect(Collectors.toList());
        listFireDto.setInhabitantsList(inhabitantsList);
        return listFireDto;
    }

}
