package com.manage.reactive.apis.personapis.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.manage.reactive.apis.personapis.domain.dto.PersonDto;
import com.manage.reactive.apis.personapis.domain.entity.Person;
import com.manage.reactive.apis.personapis.domain.repository.PersonRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {
    
    private final PersonRepository personRepository;

    //insert case
    public Mono<String> insert(PersonDto personDto){

        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
        personRepository.save(person).then();

        return Mono.just("Insert - Success");

    }

    
    //read All
    public Flux<PersonDto> getAllPerson(){
        //Entity to Dto
        return personRepository.findAll().map(person -> {
            PersonDto personDto = new PersonDto();
            BeanUtils.copyProperties(person, personDto);
            return personDto; //flatMap을 사용하면 하나씩 return되게 됨.
        });
    }


    //update
    public Mono<Void> update(PersonDto personDto){
        Mono<Person> monoPerson = personRepository.findById(personDto.getId()).map(person ->{
            BeanUtils.copyProperties(personDto, person, "id");
            return person;
        });

        //publisher째 저장할수 있다.
        return personRepository.saveAll(monoPerson).then();
    }


}
