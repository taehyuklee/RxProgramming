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
public class Person implements Persistable<String> {

    @Transient
    private boolean isNew = true;

    @Id
    private String id;

    private String email;

    private String name;

    private String phoneNum;

    private Integer score;


    @Override
    public boolean isNew() {
        return isNew;
    }
    
}