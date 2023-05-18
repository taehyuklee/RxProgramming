package com.manage.reactive.apis.personapis.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.reactive.apis.common.exception.ServerRuntimeException;
import com.manage.reactive.apis.common.response.Response;
import com.manage.reactive.apis.common.response.StatusEnums;
import com.manage.reactive.apis.personapis.domain.dto.TeamDto;
import com.manage.reactive.apis.personapis.domain.entity.Team;
import com.manage.reactive.apis.personapis.domain.repository.TeamRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    //insert case
    @Transactional
    public Mono<String> insert(TeamDto teamDto){
        Team teamEntity = new Team();
        BeanUtils.copyProperties(teamDto, teamEntity);
        //PK를 uuid로 관리하기 위함, 그리고 insert이므로 isNew를 true로 바꿔줘야 한다.
        teamEntity.setId(UUID.randomUUID().toString())
                    .setNew(true).setCretId("cretHost"); 

        //OneToMany Relation (save)
        return teamRepository.save(teamEntity).then(Response.responseOk);
    }

    //readAll
    @Transactional
    public Flux<TeamDto> getAllTeam(){
        //Entity to Dto (mapping)
        return teamRepository.findAll().map(team -> { //map을 쓰면 빨간줄 flatMap을 사용하면 괜찮다 차이가 뭘까?
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(team, teamDto);
            return teamDto;   
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




    /* If you want to use Response Object */

    //insert case with Response
    @Transactional
    public Mono<Response<Void>> insertResponse(TeamDto teamDto){

        Team teamEntity = new Team();
        BeanUtils.copyProperties(teamDto, teamEntity);

        teamEntity.setId(UUID.randomUUID().toString()).setNew(true)
                    .setCretId("cretHost"); 

        //saveDply & convert dply to mngt
        return checkDuplication(teamDto)
                .flatMap(bool->{
                    if(!bool){
                        return teamRepository.save(teamEntity)
                            .then(new Response<Void>().responseOk());
                    }else{
                        return Mono.error(new ServerRuntimeException(StatusEnums.CONFLICT))
                                .then(Mono.empty());
                    }
                }).onErrorResume(error ->{
                    return new Response<Void>().conflictError();
                });
                
    }

    //readAll
    @Transactional(readOnly = true)
    public Mono<Response<List<TeamDto>>> getResponseTeam(String teamName){

        if (teamName != null) {
            return teamRepository.findByTeamName(teamName).map(team->{
                TeamDto teamDto = new TeamDto();
                BeanUtils.copyProperties(team, teamDto);
                return teamDto;
            }).collectList().flatMap(listDto -> {
                return new Response<List<TeamDto>>().responseOk(listDto);
            });
                
        }

        return teamRepository.findAll().map(team->{
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(team, teamDto);
            return teamDto;
        }).collectList().flatMap(listDto -> {
            return new Response<List<TeamDto>>().responseOk(listDto);
        });
    
    }


    //Check of Duplication for Team Name
    public Mono<Boolean> checkDuplication(TeamDto teamDto){
        return teamRepository.existsByTeamName(teamDto.getTeamName());
    }
        
    

}
