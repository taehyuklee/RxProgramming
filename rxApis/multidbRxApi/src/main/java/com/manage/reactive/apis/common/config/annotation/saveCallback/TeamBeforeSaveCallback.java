package com.manage.reactive.apis.common.config.annotation.saveCallback;

import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

import com.manage.reactive.apis.personapis.domain.entity.Team;

import reactor.core.publisher.Mono;

@Component
class TeamBeforeSaveCallback implements BeforeSaveCallback<Team> {

    @Override
    public Publisher<Team> onBeforeSave(Team entity, OutboundRow row, SqlIdentifier table) {
            // 필드를 제외하고 새로운 객체를 생성합니다.
            Team teamWithoutMembers = new Team();
            teamWithoutMembers.setId(entity.getId());
            teamWithoutMembers.setTeamName(entity.getTeamName());
            teamWithoutMembers.setTeamGrade(entity.getTeamGrade());
        
            // 새로운 객체를 저장합니다.
            return Mono.just(teamWithoutMembers);
    }
}