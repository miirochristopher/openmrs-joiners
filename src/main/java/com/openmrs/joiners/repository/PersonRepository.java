package com.openmrs.joiners.repository;

import com.openmrs.joiners.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Override
    boolean existsById(Integer id);
}
