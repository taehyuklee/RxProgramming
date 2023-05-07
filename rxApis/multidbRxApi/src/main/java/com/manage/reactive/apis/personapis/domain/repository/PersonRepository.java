package com.manage.reactive.apis.personapis.domain.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.manage.reactive.apis.personapis.domain.entity.Person;

import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveSortingRepository<Person, String>{

    Mono<Void> deleteById(String id);
    Mono<Person> findByEmail(String email);

}
