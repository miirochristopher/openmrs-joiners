package com.openmrs.joiners.service;

import com.openmrs.joiners.entity.Person;
import com.openmrs.joiners.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void getAllPersons(){
        Person person1 = new Person(1, "Timothy Buyondo", "Mpigi");
        Person person2 = new Person(2, "Resty Tuta", "Nakisunga");

        given(personRepository.findAll())
                .willReturn(List.of(person1, person2));

        List<Person> personList = personService.getAllPersons();

        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(2);
    }
}
