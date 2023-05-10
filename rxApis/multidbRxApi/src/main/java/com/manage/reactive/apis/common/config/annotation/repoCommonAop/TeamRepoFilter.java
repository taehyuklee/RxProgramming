package com.manage.reactive.apis.common.config.annotation.repoCommonAop;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import com.manage.reactive.apis.common.config.annotation.RxTransient;
import com.manage.reactive.apis.personapis.domain.entity.Person;
import com.manage.reactive.apis.personapis.domain.entity.Team;
import com.manage.reactive.apis.personapis.domain.repository.TeamRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

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
        
                        if (targetField.isAnnotationPresent(RxTransient.class)) {
        
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
                if (targetField.isAnnotationPresent(RxTransient.class)) {

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

        Team entity = new Team();

        if (dbType.equals("rdb")) {

            Class<?> entityClass = entity.getClass(); //Entity의 .class를 가져온다.
            Field[] fields = entityClass.getDeclaredFields(); //entity의 속성들을 가져온다.

            //field를 돌면서 Annotation을 확인하도록 한다.
            for (Field targetField : fields) {

                if (targetField.isAnnotationPresent(RxTransient.class)) {

                    Flux<Mono<Tuple3<String, Team, Person>>> rslt =  dataBaseClient.sql("SELECT p.id, p.email, p.name, p.phone_num, p.score, p.team_id, p.cret_id AS person_cret_id, p.upd_id AS person_upd_id, p.cret_dt AS person_cret_dt, p.upd_dt As person_upd_dt"+
                                                                    ", t.team_name, t.team_grade, t.cret_id AS team_cret_id, t.upd_id AS team_upd_id, t.cret_dt AS team_cret_dt, t.upd_dt AS team_upd_id FROM person p INNER JOIN team t ON p.team_id = t.id")  
                        .map(row -> {
                            String teamId = row.get("team_id", String.class);
                            Team team = new Team();
                            team.setTeamGrade(row.get("team_grade", String.class))
                                    .setId(teamId)
                                    .setTeamName(row.get("team_name", String.class))
                                    .setCretId(row.get("team_cret_id", String.class))
                                    .setUpdId(row.get("team_upd_id", String.class))
                                    .setUpdDt(row.get("team_upd_id", LocalDateTime.class))
                                    .setCretDt(row.get("team_cret_dt", LocalDateTime.class));

                            
                            Person person = new Person();
                            person.setId(row.get("id", String.class))
                                    .setEmail(row.get("email", String.class))
                                    .setName(row.get("name", String.class))
                                    .setPhoneNum(row.get("phone_num", String.class))
                                    .setScore(row.get("score", Integer.class))
                                    .setCretDt(row.get("person_cret_dt", LocalDateTime.class))
                                    .setCretId(row.get("person_cret_id", String.class))
                                    .setUpdDt(row.get("person_upd_dt", LocalDateTime.class))
                                    .setUpdId(row.get("person_upd_id", String.class));

                            return Mono.just(Tuples.of(teamId, team, person));
                        }).all();


                        Flux<Team> teamFlux = rslt
                            .flatMap(monoTuple -> monoTuple.map(tuple -> Tuples.of(tuple.getT1(), tuple.getT2(), tuple.getT3())))
                            .collectMultimap(Tuple3::getT1) //grouping됨.
                            .flatMapMany(map -> Flux.fromIterable(map.entrySet()))
                            .map(entry -> {
                                String teamId = entry.getKey();
                                List<Tuple3<String,Team, Person>> tuples = new ArrayList<>(entry.getValue());
                                List<Person> persons = tuples.stream().map(Tuple3::getT3).collect(Collectors.toList());
                                Team team = tuples.get(0).getT2().setTeamMembers(persons);

                                return team;
                            });

                    return teamFlux;                   
                }
            }

        }
                
        return teamRepository.findAll();
    }
    

    public Mono<Team> findById(String id){
        return teamRepository.findById(id);
    }
    
}
