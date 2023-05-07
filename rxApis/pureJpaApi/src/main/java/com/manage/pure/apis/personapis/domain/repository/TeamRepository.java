package com.manage.pure.apis.personapis.domain.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.manage.pure.apis.personapis.domain.entity.Team;


@Repository
public interface TeamRepository extends PagingAndSortingRepository<Team, String>{

    List<Team> findAll();

}
