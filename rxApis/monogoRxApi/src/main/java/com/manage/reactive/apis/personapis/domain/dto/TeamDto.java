package com.manage.reactive.apis.personapis.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.manage.reactive.apis.personapis.domain.entity.Person;

import lombok.Data;

@Data
public class TeamDto {

    private String id;

    @NotNull
    @Size(min = 1, max = 20)
    private String teamName;

    @NotEmpty
    private String teamGrade;
    
    private List<Person> teamMembers;

    private String cretId;

    private String updId;

    private LocalDateTime cretDt;

    private LocalDateTime updDt;

}
