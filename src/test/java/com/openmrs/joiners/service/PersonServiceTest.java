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
    void saveOrUpdatePerson(){
        //Person record to be fetched from the DB for updating
        Person existingPerson = new Person(4, "Okwere Donald", "Washiso");

        //updated Result
        Person updatedPerson = new Person(4, "Mutebi Martin", "Kampala");

        //Expected result by the test
        Person expectedUpdated = new Person(4, "Mutebi Martin", "Kampala");

        when(personRepository.findById(4)).thenReturn(Optional.of(existingPerson));
        when(personRepository.save(existingPerson)).thenReturn(expectedUpdated);

        Person result = personService.saveOrUpdatePerson(updatedPerson);

        // Verifications
        verify(personRepository, times(1)).findById(4);
        verify(personRepository, times(1)).save(existingPerson);

        // Assertions
        assertThat(result).isNotNull();
        assertThat(result.getPersonName()).isEqualTo("Mutebi Martin");
        assertThat(result.getPersonAddress()).isEqualTo("Kampala");
    }
}
