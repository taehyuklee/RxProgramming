package com.manage.reactive.apis.common.config.beforeSaveCallback;

import java.lang.reflect.Field;

import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

import com.manage.reactive.apis.common.config.annotation.ConditionalTransient;

import reactor.core.publisher.Mono;


@Component
public class ConditionalTransientCallback implements BeforeConvertCallback<Object> {


    @Override
    public Publisher<Object> onBeforeConvert(Object entity, SqlIdentifier table) {
        
        Class<?> entityClass = entity.getClass(); //Entity의 .class를 가져온다.
        Field[] fields = entityClass.getDeclaredFields(); //entity의 속성들을 가져온다.

        //field를 돌면서 Annotation을 확인하도록 한다.
        for (Field field : fields) {
            //field.isAnnotationPresent로 확인 가능 여기서 내가 만들어 놓은 ConditionalTransient annotation을 확인한다.
            if (field.isAnnotationPresent(ConditionalTransient.class)) {
                ConditionalTransient annotation = field.getAnnotation(ConditionalTransient.class);
                String rdbType = annotation.dbType();

                if (!rdbType.equals("rdb")) {
                    field.setAccessible(true);
                    try {
                        field.set(entity, null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return Mono.just(entity);
       
    }

}