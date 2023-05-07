package com.manage.reactive.apis.personapis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.manage.reactive.apis.personapis.domain.dto.PersonDto;
import com.manage.reactive.apis.personapis.service.PersonService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reactive")
public class PersonController {

    private final PersonService personService;

    //Insert
    @PostMapping("/insert")
    public Mono<String> insert(@RequestBody PersonDto personDto){
        return personService.insert(personDto);
    }

    //Read
    /* 
     *  Flux<PersonDto>로 return해줘도 되는 이유 :
     *  Accept: application/stream+json 형식을 헤더에 설정하면 서버는 응답을 스트림 형태로 전송하게 된다.
     */
    @GetMapping("/read")
    public Flux<PersonDto> getAllPerson(){
        return personService.getAllPerson();
    }


    //getOnePerson
    @GetMapping("/getByEmail")
    public Mono<PersonDto> getPersonByEmail(String email){
        return personService.getPersonByEmail(email);
    }


    //Update
    @PutMapping("/update")
    public Mono<String> updatePerson(@RequestBody PersonDto personDto){
        return personService.update(personDto);
    }

    //Delete
    @DeleteMapping("/delete")
    public Mono<String> deletePerson(String id){
        return personService.delete(id);
    }
 
}
