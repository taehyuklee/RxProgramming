package com.manage.reactive.apis.personapis.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.manage.reactive.apis.personapis.domain.entity.Person;

public interface PersonRepository extends ReactiveMongoRepository<Person, String>{

    
    
}
