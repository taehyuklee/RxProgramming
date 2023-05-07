package com.manage.pure.apis.personapis.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Person extends AuditEntity{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String email;

    private String name;

    private String phoneNum;

    private Integer score;
    
}