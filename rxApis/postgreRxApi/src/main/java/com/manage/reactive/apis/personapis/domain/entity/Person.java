package com.manage.reactive.apis.personapis.domain.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Table("Person")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Person implements Persistable<String> {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String email;

    private String name;

    private String phoneNum;

    private Integer score;

    @Override
    public boolean isNew() {
        return true;
    }
    
}
