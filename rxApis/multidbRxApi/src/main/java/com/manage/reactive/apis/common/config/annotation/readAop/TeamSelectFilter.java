package com.manage.reactive.apis.common.config.annotation.readAop;

import org.springframework.stereotype.Component;

import com.manage.reactive.apis.personapis.domain.entity.Team;
import com.manage.reactive.apis.personapis.domain.repository.TeamRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class TeamSelectFilter {
    
    private final TeamRepository teamRepository;

    public Flux<Team> findAll(){

        return teamRepository.findAll();
    }
    
}
