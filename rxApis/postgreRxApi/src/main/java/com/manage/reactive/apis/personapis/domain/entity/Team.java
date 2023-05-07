package com.manage.reactive.apis.personapis.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Team")
@Accessors(chain = true)
public class Team extends AuditEntity implements Persistable<String>{

    @Transient
    private boolean isNew;

    @Id
    private String id;

    private String teamName;

    private String teamGrade;

    @Transient
    private List<Person> teamMembers;
    
    @Override
    public boolean isNew() {
        return isNew;
    }
    
}
