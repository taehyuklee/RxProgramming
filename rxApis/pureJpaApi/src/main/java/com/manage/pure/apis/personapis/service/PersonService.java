package com.manage.pure.apis.personapis.service;
// package com.manage.pure.apis.personapis.service;

// import java.util.UUID;

// import org.springframework.beans.BeanUtils;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.manage.pure.apis.common.response.Response;
// import com.manage.pure.apis.personapis.domain.dto.PersonDto;
// import com.manage.pure.apis.personapis.domain.entity.Person;
// import com.manage.pure.apis.personapis.domain.repository.PersonRepository;

// import lombok.RequiredArgsConstructor;
// import reactor.core.publisher.Flux;
// import reactor.core.publisher.Mono;

// @Service
// @RequiredArgsConstructor
// public class PersonService {
    
//     private final PersonRepository personRepository;

//     //insert case
//     @Transactional
//     public Mono<String> insert(PersonDto personDto){
//         Person personEntity = new Person();
//         BeanUtils.copyProperties(personDto, personEntity);
//         //PK를 uuid로 관리하기 위함, 그리고 insert이므로 isNew를 true로 바꿔줘야 한다.
//         personEntity.setId(UUID.randomUUID().toString())
//                     .setNew(true).setCretId("cretHost"); 
//         return personRepository.save(personEntity).then(Response.responseOk);

//     }

    
//     //readAll
//     @Transactional
//     public Flux<PersonDto> getAllPerson(){
//         //Entity to Dto (mapping)
//         return personRepository.findAll().map(person -> {
//             PersonDto personDto = new PersonDto();
//             BeanUtils.copyProperties(person, personDto);
//             return personDto; //flatMap을 사용하면 하나씩 return되게 됨.
//         });
//     }


//     //getPersonByEmail
//     @Transactional
//     public Mono<PersonDto> getPersonByEmail(String email){
//         return personRepository.findByEmail(email).map(person -> {
//             PersonDto personDto = new PersonDto();
//             BeanUtils.copyProperties(person, personDto);
//             return personDto;
//         });
//     }


//     //update
//     @Transactional
//     public Mono<String> update(PersonDto personDto){
//         Mono<Person> monoPerson = personRepository.findById(personDto.getId()).map(person ->{
//             BeanUtils.copyProperties(personDto, person, "id");
//             person.setNew(false).setUpdId("updHost"); //update이므로 false로 바꿔줘야 한다.
//             return person;
//         });

//         //publisher째 저장할수 있다.
//         return personRepository.saveAll(monoPerson).then(Response.responseOk);
//     }


//     //delete
//     @Transactional
//     public Mono<String> delete(String id){
//         return personRepository.deleteById(id).then(Response.responseOk);
//     }

// }
