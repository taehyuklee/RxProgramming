package com.manage.reactive.apis.personapis.domain.dto;

import java.util.List;

import com.manage.reactive.apis.personapis.domain.entity.Person;

import lombok.Data;

@Data
public class TeamDto {

    private String id;

    private String teamName;

    private String teamGrade;
    
    private List<Person> teamMembers;

}
