package com.openmrs.joiners.service;

import com.openmrs.joiners.entity.Person;
import com.openmrs.joiners.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void getAllPersons() {
        Person person1 = new Person(1, "Timothy Buyondo", "Mpigi");
        Person person2 = new Person(2, "Resty Tuta", "Nakisunga");

        given(personRepository.findAll())
                .willReturn(List.of(person1, person2));

        List<Person> personList = personService.getAllPersons();

        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(2);
    }

    @Test
    void savePerson() {
        Person person = new Person(3, "John Smith", "Kampala");

        when(personService.savePerson(person)).thenReturn(person);
        Person personToSave = personService.savePerson(person);
        verify(personRepository,times(1)).save(person);

        assertThat(personToSave).isNotNull();
        assertThat(personToSave.getPersonName()).isEqualTo("John Smith");
        assertThat(personToSave.getPersonAddress()).isEqualTo("Kampala");
    }

    @Test
    void updatePerson(){
        // Existing person in DB
        Person person1 = new Person();
        person1.setPersonId(1);
        person1.setPersonName("Mutumba Martin");
        person1.setPersonAddress("Kawempe");

        // new update object
        Person newPerson = new Person();
        newPerson.setPersonId(1);
        newPerson.setPersonName("Muwonge Rashid");
        newPerson.setPersonAddress("Kampala");

        when(personRepository.findById(1)).thenReturn(Optional.of(person1));
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Perform the update
        Person result = personService.updatePerson(newPerson);

        // Verify interactions
        verify(personRepository, times(1)).findById(1);
        verify(personRepository, times(1)).save(person1);

        // Assert changes
        assertThat(result).isNotNull();
        assertThat(result.getPersonName()).isEqualTo("Muwonge Rashid");
        assertThat(result.getPersonAddress()).isEqualTo("Kampala");
    }
}
