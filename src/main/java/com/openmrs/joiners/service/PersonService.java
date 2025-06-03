package com.openmrs.joiners.service;

import com.openmrs.joiners.entity.Person;
import com.openmrs.joiners.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Person saveOrUpdatePerson(Person updatedPerson){
        int personId = updatedPerson.getPersonId();
        Person existingPerson = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with ID: " + personId));

        // Update fields
        existingPerson.setPersonName(updatedPerson.getPersonName());
        existingPerson.setPersonAddress(updatedPerson.getPersonAddress());

        return personRepository.save(existingPerson);
    }
}
