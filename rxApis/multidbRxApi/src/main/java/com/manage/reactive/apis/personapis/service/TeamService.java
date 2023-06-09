package com.manage.reactive.apis.personapis.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.reactive.apis.common.config.annotation.repoCommonAop.TeamRepoFilter;
import com.manage.reactive.apis.common.response.Response;
import com.manage.reactive.apis.personapis.domain.dto.TeamDto;
import com.manage.reactive.apis.personapis.domain.entity.Team;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TeamService {

    //private final TeamRepository teamRepository;
    private final TeamRepoFilter teamRepoFilter;
    
    //insert case
    @Transactional
    public Mono<String> insert(TeamDto teamDto){
        Team teamEntity = new Team();
        BeanUtils.copyProperties(teamDto, teamEntity);
        //PK를 uuid로 관리하기 위함, 그리고 insert이므로 isNew를 true로 바꿔줘야 한다.
        teamEntity.setId(UUID.randomUUID().toString())
                    .setNew(true).setCretId("cretHost"); 
        return teamRepoFilter.save(teamEntity).then(Response.responseOk);
    }

    //readAll
    @Transactional
    public Flux<TeamDto> getAllTeam(){
        //Entity to Dto (mapping)
        return teamRepoFilter.findAll().map(team -> {
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(team, teamDto);
            return teamDto; //flatMap을 사용하면 하나씩 return되게 됨.
        });
    }

    //update
    @Transactional
    public Mono<String> update(TeamDto teamDto){
        return teamRepoFilter.findById(teamDto.getId())
        .flatMap(team -> {
            BeanUtils.copyProperties(teamDto, team, "id");
            team.setNew(false).setUpdId("updHost");
            return teamRepoFilter.save(team);
        })
        .then(Response.responseOk);
    }

    //delete
    @Transactional
    public Mono<String> delete(String id){
        return teamRepoFilter.deleteById(id).then(Response.responseOk);
    }
    

}
