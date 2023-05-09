package com.manage.reactive.apis.common.config.annotation.repoCommonAop;

import java.lang.reflect.Field;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Sort;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import com.manage.reactive.apis.common.config.annotation.ConditionalTransient;
import com.manage.reactive.apis.personapis.domain.entity.Team;
import com.manage.reactive.apis.personapis.domain.repository.TeamRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@ConfigurationProperties
@RequiredArgsConstructor
public class TeamRepoFilter{
    
    @Value("${common.db}")
    private String dbType;

    private final DatabaseClient dataBaseClient;

    private final TeamRepository teamRepository;

    //save (LifeCycle상에 구현 해놓음)
    public Mono<Team> save(Team entity){
        return teamRepository.save(entity);
    }


    //deleteById (java reflect로 여기서 구현)
    public Mono<Void> deleteById(String id){

        if (dbType.equals("rdb")) {
            Mono<Void> query = teamRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Team not found with id: " + id)))
                .flatMap(entity ->{
                    Class<?> entityClass = entity.getClass(); //Entity의 .class를 가져온다.
                    Field[] fields = entityClass.getDeclaredFields(); //entity의 속성들을 가져온다.
        
                    //field를 돌면서 Annotation을 확인하도록 한다.
                    for (Field targetField : fields) {
        
                        if (targetField.isAnnotationPresent(ConditionalTransient.class)) {
        
                            Mono<Void> deletePerson = dataBaseClient.sql("DELETE FROM person WHERE team_id = :team_id")
                                .bind("team_id", id)
                                .then();
        
                            Mono<Void> deleteTeam = dataBaseClient.sql("DELETE FROM team WHERE id = :id")
                                .bind("id", id)
                                .then();
        
                        //db는 실행시켜줘야함.
                        return deletePerson.then(deleteTeam).then();        
                        }
                    }
                    return null;

                }).then();
            
                return query;
            }
        return teamRepository.deleteById(id);
    }


    //delete(entity)
    public Mono<Void> delete(Team entity){
        if (dbType.equals("rdb")) {
            Class<?> entityClass = entity.getClass(); //Entity의 .class를 가져온다.
            Field[] fields = entityClass.getDeclaredFields(); //entity의 속성들을 가져온다.

            //field를 돌면서 Annotation을 확인하도록 한다.
            for (Field targetField : fields) {
                if (targetField.isAnnotationPresent(ConditionalTransient.class)) {

                    Mono<Void> deletePerson = dataBaseClient.sql("DELETE FROM person WHERE team_id = :team_id")
                        .bind("team_id", entity.getId())
                        .then();

                    Mono<Void> deleteTeam = dataBaseClient.sql("DELETE FROM team WHERE id = :id")
                        .bind("id", entity.getId())
                        .then();

                //db는 실행시켜줘야함.
                return deletePerson.then(deleteTeam).then();        
                }
            }

        }
        return teamRepository.delete(entity);
    }


    public Flux<Team> findAll(){
        return teamRepository.findAll();
    }
    
    
    public Mono<Team> findById(String id){
        return teamRepository.findById(id);
    }

    
}
