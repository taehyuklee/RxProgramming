package com.manage.reactive.apis.common.config.annotation.saveCallback;


import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.UUID;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import com.manage.reactive.apis.personapis.domain.entity.Person;
import com.manage.reactive.apis.personapis.domain.entity.Team;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.manage.reactive.apis.common.config.annotation.ConditionalTransient;

@Component
@ConfigurationProperties
@RequiredArgsConstructor
public class TeamCallback implements BeforeConvertCallback<Team> {

    @Value("${common.db}")
    private String dbType;

    private final DatabaseClient dataBaseClient;

    @Override
    public Publisher<Team> onBeforeConvert(Team entity, SqlIdentifier table) {

        if (dbType.equals("rdb")) {

            Class<?> entityClass = entity.getClass(); //Entity의 .class를 가져온다.
            Field[] fields = entityClass.getDeclaredFields(); //entity의 속성들을 가져온다.

            LocalDateTime currTime = LocalDateTime.now();

            //field를 돌면서 Annotation을 확인하도록 한다.
            for (Field targetField : fields) {
                //field.isAnnotationPresent로 확인 가능 여기서 내가 만들어 놓은 ConditionalTransient annotation을 확인한다.
                if (targetField.isAnnotationPresent(ConditionalTransient.class)) {

                    Mono<Void> insertTeam = dataBaseClient.sql("INSERT INTO team (id, team_name, team_grade, cret_dt, upd_dt, cret_id) VALUES(:id, :team_name, :team_grade, :cret_dt, :upd_dt, :cret_id)")
                                                        .bind("id", entity.getId())
                                                        .bind("team_name", entity.getTeamName())
                                                        .bind("team_grade", entity.getTeamGrade())
                                                        .bind("cret_dt", currTime)
                                                        .bind("upd_dt", currTime)
                                                        .bind("cret_id", "cretHost")
                                                        .then();


                    Mono<Void> insertPeople = Flux.fromIterable(entity.getTeamMembers())
                            .flatMap(person -> dataBaseClient.sql("INSERT INTO person (id, email, name, phone_num, score, cret_dt, upd_dt, cret_id, team_id) VALUES(:id, :email, :name, :phone_num, :score, :cret_dt, :upd_dt, :cret_id, :team_id)")
                                    .bind("id", UUID.randomUUID().toString())
                                    .bind("team_id", entity.getId())
                                    .bind("email", person.getEmail())
                                    .bind("name", person.getName())
                                    .bind("phone_num",person.getPhoneNum())
                                    .bind("score", person.getScore())
                                    .bind("cret_dt", currTime)
                                    .bind("upd_dt", currTime)
                                    .bind("cret_id", "cretHost").then())
                                .then();
                         
                                
                    
                    return Mono.zip(insertTeam, insertPeople)
                        .then(Mono.empty());
                    }
                }
            }
                
            return Mono.just(entity);
            
        }
}
