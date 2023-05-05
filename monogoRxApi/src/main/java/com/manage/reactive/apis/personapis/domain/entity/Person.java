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
    String id;

    String email;

    String name;

    String phoneNum;

    Integer score;
    
}
