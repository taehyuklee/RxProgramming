package com.manage.reactive.apis.common.config.annotation.deleteAop;

import org.springframework.data.relational.core.conversion.MutableAggregateChange;
import org.springframework.data.relational.core.mapping.event.BeforeDeleteCallback;

import com.manage.reactive.apis.personapis.domain.entity.Team;


public class TeamBeforeDelete implements BeforeDeleteCallback<Team>{

    @Override
    public Team onBeforeDelete(Team team, MutableAggregateChange<Team> aggregateChange) {
        
        //자바 Reflection으로 chain으로 삭제할수 있도록 한다. (annotatino확인하고)

        return team;
    }

    
}
