package com.dynamicformgeneration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dynamicformgeneration.entities.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer> {

}
