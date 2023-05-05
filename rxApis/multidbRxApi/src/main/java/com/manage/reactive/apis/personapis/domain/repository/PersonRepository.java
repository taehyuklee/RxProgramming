package com.manage.reactive.apis.personapis.domain.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.manage.reactive.apis.personapis.domain.entity.Person;

import reactor.core.publisher.Mono;

public interface PersonRepository extends R2dbcRepository<Person, String>{

    Mono<Void> deleteById(String id);
    Mono<Person> findByEmail(String email);
    
}
