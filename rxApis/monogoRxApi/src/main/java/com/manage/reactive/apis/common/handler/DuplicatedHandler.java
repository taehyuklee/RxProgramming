package com.manage.reactive.apis.common.handler;

import com.manage.reactive.apis.common.exception.ServerRuntimeException;
import com.manage.reactive.apis.common.response.Response;
import com.manage.reactive.apis.common.response.StatusEnums;
import com.manage.reactive.apis.personapis.domain.entity.Team;
import com.manage.reactive.apis.personapis.domain.repository.TeamRepository;

import reactor.core.publisher.Mono;

public class DuplicatedHandler{

    public static Mono<Response<Void>> checkAndSave(TeamRepository teamRepository, Team teamEntity){
        return teamRepository.existsByTeamName(teamEntity.getTeamName())
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

    
}
