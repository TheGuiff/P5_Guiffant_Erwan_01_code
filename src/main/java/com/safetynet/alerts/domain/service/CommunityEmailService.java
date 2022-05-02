package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.repository.PersonRepository;
import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.web.dto.CommunityEmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommunityEmailService {

    @Autowired
    PersonRepository personRepository;

    public CommunityEmailDto communityEmail (String city) {
        List<String> listEmail =  personRepository.getListPersons().stream()
                .filter(person -> Objects.equals(person.getCity(), city))
                .map(Person::getEmail)
                .collect(Collectors.toList());
        CommunityEmailDto communityEmailDto = new CommunityEmailDto();
        communityEmailDto.setListEmail(listEmail);
        return communityEmailDto;
    }
}
