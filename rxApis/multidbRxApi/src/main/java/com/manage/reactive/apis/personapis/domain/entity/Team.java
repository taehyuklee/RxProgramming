package com.manage.reactive.apis.personapis.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Table;

import com.manage.reactive.apis.common.config.annotation.RxJoinColumn;
import com.manage.reactive.apis.common.config.annotation.RxTransient;


@Data
@Table("Team")
@Document
@Accessors(chain = true)
public class Team extends AuditEntity implements Persistable<String>{

    @Transient
    private boolean isNew;

    @Id
    private String id;

    private String teamName;

    private String teamGrade;

    @RxTransient
    @RxJoinColumn(name = "team_id")
    private List<Person> teamMembers = new ArrayList<Person>();
    
    @Override
    public boolean isNew() {
        return isNew;
    }
    
}
