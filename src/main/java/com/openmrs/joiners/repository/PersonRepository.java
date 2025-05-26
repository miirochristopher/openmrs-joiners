package com.openmrs.joiners.repository;

import com.openmrs.joiners.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
