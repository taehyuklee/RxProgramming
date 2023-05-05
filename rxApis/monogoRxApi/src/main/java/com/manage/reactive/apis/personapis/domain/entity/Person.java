package com.manage.reactive.apis.personapis.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Document
@Accessors(chain = true)
public class Person {

    @Id
    private String id;

    private String email;

    private String name;

    private String phoneNum;

    private Integer score;
    
}
