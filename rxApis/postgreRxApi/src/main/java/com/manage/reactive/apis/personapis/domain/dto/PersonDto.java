package com.manage.reactive.apis.personapis.domain.dto;

import lombok.Data;

@Data
public class PersonDto {
    
    private String id;

    //FK - Person PK
    private String teamId;

    private String email;

    private String name;

    private String phoneNum;

    private Integer score;

}
