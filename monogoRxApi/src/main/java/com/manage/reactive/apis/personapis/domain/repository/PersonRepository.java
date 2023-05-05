package com.manage.reactive.apis.personapis.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.manage.reactive.apis.personapis.domain.entity.Person;

import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveMongoRepository<Person, String>{

    Mono<Void> deleteById(String id);
    Mono<Person> findByEmail(String email);
    
}
