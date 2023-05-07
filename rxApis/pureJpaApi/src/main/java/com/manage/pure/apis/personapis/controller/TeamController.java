package com.manage.pure.apis.personapis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.pure.apis.personapis.domain.dto.TeamDto;
import com.manage.pure.apis.personapis.service.TeamService;

import org.springframework.web.bind.annotation.RequestBody;

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
    public String insert(@Valid @RequestBody TeamDto teamDto){
        return teamService.insert(teamDto);
    }

    //Read
    @GetMapping("/team")
    public List<TeamDto> getAllTeam(){
        return teamService.getAllTeam();
    }

    //Update
    @PutMapping("/team")
    public String updateTeam(@Valid @RequestBody TeamDto teamDto){
        return teamService.update(teamDto);
    }

    //delete
    @DeleteMapping("/team")
    public String deletePerson(String id){
        return teamService.delete(id);
    }
}
