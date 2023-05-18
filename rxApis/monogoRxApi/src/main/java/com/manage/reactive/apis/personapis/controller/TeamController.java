package com.manage.reactive.apis.personapis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.manage.reactive.apis.common.response.Response;
import com.manage.reactive.apis.personapis.domain.dto.TeamDto;
import com.manage.reactive.apis.personapis.service.TeamService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reactive")
public class TeamController {

    private final TeamService teamService;

    //Create
    @PostMapping("/team")
    public Mono<String> insert(@Valid @RequestBody TeamDto teamDto){
        return teamService.insert(teamDto);
    }

    //Read
    @GetMapping("/team")
    public Flux<TeamDto> getAllTeam(){
        return teamService.getAllTeam();
    }

    //Update
    @PutMapping("/team")
    public Mono<String> updateTeam(@Valid @RequestBody TeamDto teamDto){
        return teamService.update(teamDto);
    }

    //delete
    @DeleteMapping("/team")
    public Mono<String> deletePerson(String id){
        return teamService.delete(id);
    }


    //If you want to wrap your DTO with Response Dto (wrapping DTOs with Response format)
    @PostMapping("/responseTeam")
    public Mono<Response<Void>> insertResponse(@Valid @RequestBody TeamDto teamDto){
        return teamService.insertResponse(teamDto);
    }

    //Read
    @GetMapping("/responseTeam")
    public Mono<Response<List<TeamDto>>> getResponseTeam(@RequestParam(value="teamName", required = false) String teamName){
        return teamService.getResponseTeam(teamName);
    }

}
