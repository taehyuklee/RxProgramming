package com.manage.reactive.apis.personapis.domain.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.manage.reactive.apis.personapis.domain.entity.Team;


@Repository
public interface TeamRepository extends ReactiveSortingRepository<Team, String>{

}
