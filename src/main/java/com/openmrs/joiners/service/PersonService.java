package com.openmrs.joiners.service;

import com.openmrs.joiners.entity.Person;
import com.openmrs.joiners.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService{

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Person person) {
        Optional<Person> existingPerson = personRepository.findById(person.getPersonId());
        Person personToUpdate = new Person();
        if (existingPerson.isPresent()) {
            personToUpdate = existingPerson.get();

            // Update fields
            personToUpdate.setPersonName(person.getPersonName());
            personToUpdate.setPersonAddress(person.getPersonAddress());
        }
        return personRepository.save(personToUpdate);
    }
}
