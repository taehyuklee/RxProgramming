package com.manage.reactive.apis.personapis.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.reactive.apis.common.config.response.Response;
import com.manage.reactive.apis.personapis.domain.dto.TeamDto;
import com.manage.reactive.apis.personapis.domain.entity.Team;
import com.manage.reactive.apis.personapis.domain.repository.PersonRepository;
import com.manage.reactive.apis.personapis.domain.repository.TeamRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final PersonService personService;
    
    //insert case
    @Transactional
    public Mono<String> insert(TeamDto teamDto){
        Team teamEntity = new Team();
        BeanUtils.copyProperties(teamDto, teamEntity);
        //PK를 uuid로 관리하기 위함, 그리고 insert이므로 isNew를 true로 바꿔줘야 한다.
        teamEntity.setId(UUID.randomUUID().toString())
                    .setNew(true).setCretId("cretHost"); 

        //OneToMany Relation
        Mono<Void> dbSave = teamRepository.save(teamEntity).flatMap(team -> {
            return personService.svaeRelation(team); //반환 자체가 Flux이므로 flatMapMany를 사용해야한다 or Mono<Void>로 감싸서 가져온다.
        }).then();

        return dbSave.then(Response.responseOk);
    }

    //readAll
    @Transactional
    public Flux<TeamDto> getAllTeam(){
        //Entity to Dto (mapping)
        return teamRepository.findAll().map(team -> {
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(team, teamDto);
            return teamDto; //flatMap을 사용하면 하나씩 return되게 됨.
        });
    }

    //update
    @Transactional
    public Mono<String> update(TeamDto teamDto){
        return teamRepository.findById(teamDto.getId())
        .flatMap(team -> {
            BeanUtils.copyProperties(teamDto, team, "id");
            team.setNew(false).setUpdId("updHost");
            return teamRepository.save(team);
        })
        .then(Response.responseOk);
    }

    //delete
    @Transactional
    public Mono<String> delete(String id){
        return teamRepository.deleteById(id).then(Response.responseOk);
    }
    

}
