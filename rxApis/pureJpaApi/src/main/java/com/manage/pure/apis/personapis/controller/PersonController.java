package com.manage.pure.apis.personapis.controller;
// package com.manage.pure.apis.personapis.controller;

// import javax.validation.Valid;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.manage.pure.apis.personapis.domain.dto.PersonDto;
// import com.manage.pure.apis.personapis.service.PersonService;

// import org.springframework.web.bind.annotation.RequestBody;

// import lombok.RequiredArgsConstructor;
// import reactor.core.publisher.Flux;
// import reactor.core.publisher.Mono;

// @RestController
// @RequiredArgsConstructor
// @RequestMapping("/reactive")
// public class PersonController {

//     private final PersonService personService;

//     //Insert
//     @PostMapping("/person")
//     public Mono<String> insert(@Valid @RequestBody PersonDto personDto){
//         return personService.insert(personDto);
//     }

//     //Read
//     /* 
//      *  Flux<PersonDto>로 return해줘도 되는 이유 :
//      *  Accept: application/stream+json 형식을 헤더에 설정하면 서버는 응답을 스트림 형태로 전송하게 된다.
//      */
//     @GetMapping("/person")
//     public Flux<PersonDto> getAllPerson(){
//         return personService.getAllPerson();
//     }


//     //getOnePerson
//     @GetMapping("/getByEmail")
//     public Mono<PersonDto> getPersonByEmail(String email){
//         return personService.getPersonByEmail(email);
//     }


//     //Update
//     @PutMapping("/person")
//     public Mono<String> updatePerson(@Valid @RequestBody PersonDto personDto){
//         return personService.update(personDto);
//     }

//     //Delete
//     @DeleteMapping("/person")
//     public Mono<String> deletePerson(String id){
//         return personService.delete(id);
//     }
    
// }
