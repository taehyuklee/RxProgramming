package com.manage.reactive.apis.personapis.domain.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import com.manage.reactive.apis.personapis.domain.entity.Team;


public interface TeamRepository extends ReactiveSortingRepository<Team, String>{

}
