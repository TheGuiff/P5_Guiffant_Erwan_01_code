package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.web.dto.PersonDto;
import com.safetynet.alerts.domain.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("")
    public List<PersonDto> getListPersons() {
        return personService.listPersonToPersonDto(personService.getListPersons());
    }

    @GetMapping("/{firstName},{lastName}")
    public ResponseEntity<?> getPersonByFirstNameAndLastName(@PathVariable("firstName") final String firstName,
                                                     @PathVariable("lastName") final String lastName) {
        log.info("get person by firstname (" + firstName + ") and lastname (" + lastName + ")");
        try {
            personService.personToPersonDto(personService.getPersonByFirstNameAndLastName(firstName, lastName));
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            log.error("GET /person error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("") //Idem au-dessus
    public ResponseEntity<?> deletePerson(@RequestParam("firstName") final String firstName,
                                       @RequestParam("lastName") final String lastName) {
        log.info("delete person by firstname (" + firstName + ") and lastname (" + lastName + ")");
        try {
            personService.deletePerson(firstName, lastName);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            log.error("DELETE /person error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public PersonDto addPerson(@RequestBody PersonDto personDto) {
        log.info("save person with firstname (" + personDto.getFirstName() + ") and lastname (" + personDto.getLastName() + ")");
        return personService.personToPersonDto(personService.addPerson(personDto.getFirstName(),
                    personDto.getLastName(),
                    personDto.getAddress(),
                    personDto.getCity(),
                    personDto.getZip(),
                    personDto.getPhone(),
                    personDto.getEmail()));
    }

    @PutMapping("")
    public ResponseEntity<?> updatePerson(@RequestBody PersonDto personDto) {
        log.info("update person with firstname (" + personDto.getFirstName() + ") and lastname (" + personDto.getLastName() + ")");
        try {
            personService.personToPersonDto(personService.updatePerson(personDto.getFirstName(),
                    personDto.getLastName(),
                    personDto.getAddress(),
                    personDto.getCity(),
                    personDto.getZip(),
                    personDto.getPhone(),
                    personDto.getEmail()));
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            log.error("PUT /person error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
