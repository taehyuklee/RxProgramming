package com.manage.reactive.apis.personapis.domain.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import com.manage.reactive.apis.personapis.domain.entity.Team;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface TeamRepository extends ReactiveSortingRepository<Team, String>{
    Mono<Boolean> existsByTeamName(String teamName);

    //the reason why I use Flux here is that I want to fit code format coherently the reason why I use Flux here is that I want to fit service response code format coherently as "Mono<Response<List<Dto>>>"
    Flux<Team> findByTeamName(String teamName); 
}
