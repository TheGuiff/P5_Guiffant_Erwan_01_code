package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.web.dto.ChildAlertDto;
import com.safetynet.alerts.web.dto.ChildAlertMemberDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildAlertService {

    public ChildAlertDto childAlert(List<Person> listPerson) {
        ChildAlertDto childAlertDto = new ChildAlertDto();
        childAlertDto.setListChildren(listPerson.stream()
                .filter(person -> person.getAge()<=18)
                .map(person -> {
                    ChildAlertMemberDto childAlertMemberDto = new ChildAlertMemberDto();
                    childAlertMemberDto.setFirstName(person.getFirstName());
                    childAlertMemberDto.setLastName(person.getLastName());
                    childAlertMemberDto.setAge(person.getAge());
                    return childAlertMemberDto;
                })
                .collect(Collectors.toList()));
        childAlertDto.setListAdults(listPerson.stream()
                .filter(person -> person.getAge()>18)
                .map(person -> {
                    ChildAlertMemberDto childAlertMemberDto = new ChildAlertMemberDto();
                    childAlertMemberDto.setFirstName(person.getFirstName());
                    childAlertMemberDto.setLastName(person.getLastName());
                    childAlertMemberDto.setAge(person.getAge());
                    return childAlertMemberDto;
                })
                .collect(Collectors.toList()));
        return childAlertDto;
    }
}
