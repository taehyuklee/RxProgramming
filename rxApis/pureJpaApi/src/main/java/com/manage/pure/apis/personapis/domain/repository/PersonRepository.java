package com.manage.pure.apis.personapis.domain.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.manage.pure.apis.personapis.domain.entity.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, String>{

    void deleteById(String id);
    Person findByEmail(String email);
    List<Person> findAll();

}
