package com.manage.reactive.apis.personapis.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Table("Person")
@Document
@Accessors(chain = true)
public class Person extends AuditEntity implements Persistable<String>{

    @Transient
    private boolean isNew;

    @Id
    private String id;

    //mongodb일때는 안넣어주면 된다. rdb일때만 FK주입해주면 됨.
    //private String teamId; 

    private String email;

    private String name;

    private String phoneNum;

    private Integer score;

    @Override
    public boolean isNew() {
        return isNew;
    }
    
}