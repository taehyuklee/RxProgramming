package com.manage.reactive.apis.common.config.annotation.saveCallback;

import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;

import com.manage.reactive.apis.personapis.domain.entity.Team;

public class TeamBeforeSaveCallback implements BeforeSaveCallback<Team>{

    @Override
    public Publisher onBeforeSave(Object entity, OutboundRow row, SqlIdentifier table) { 
    }
    
}
