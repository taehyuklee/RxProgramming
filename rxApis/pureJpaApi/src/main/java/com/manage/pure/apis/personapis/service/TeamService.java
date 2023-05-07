package com.manage.pure.apis.personapis.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.pure.apis.common.response.Response;
import com.manage.pure.apis.personapis.domain.dto.TeamDto;
import com.manage.pure.apis.personapis.domain.entity.Team;
import com.manage.pure.apis.personapis.domain.repository.TeamRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    
    //insert case
    @Transactional
    public String insert(TeamDto teamDto){
        Team teamEntity = new Team();
        BeanUtils.copyProperties(teamDto, teamEntity);
        //PK를 uuid로 관리하기 위함, 그리고 insert이므로 isNew를 true로 바꿔줘야 한다.
        teamRepository.save(teamEntity);
        return "Success";
    }

    //readAll
    @Transactional
    public List<TeamDto> getAllTeam(){
        return teamRepository.findAll().stream().map(entity -> {
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(entity, teamDto);
            return teamDto;
        }).toList();
    }

    //update
    @Transactional
    public String update(TeamDto teamDto){
        Team teamEntity = teamRepository.findById(teamDto.getId()).get();
        BeanUtils.copyProperties(teamDto, teamEntity, "id");
        teamRepository.save(teamEntity);
        return "Success";
    }

    //delete
    @Transactional
    public String delete(String id){
        teamRepository.deleteById(id);
        return "Sucess";
    }
    

}
