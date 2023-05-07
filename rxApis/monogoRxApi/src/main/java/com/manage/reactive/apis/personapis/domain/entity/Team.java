package com.manage.reactive.apis.personapis.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
@Accessors(chain = true)
public class Team extends AuditEntity{

    @Transient
    private boolean isNew;

    @Id
    private String id;

    private String teamName;

    private String teamGrade;

    private List<Person> teamMembers;

}
