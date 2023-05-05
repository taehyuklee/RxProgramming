package com.manage.reactive.apis.common.config.r2dbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.config.PersistentEntitiesFactoryBean;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@ConditionalOnProperty(
        value = "common.db"
        ,havingValue = "rdb"
        ,matchIfMissing = false)
@EnableR2dbcRepositories(basePackages = "com.manage.reative.apis.personapis.domain.repository")
@EnableR2dbcAuditing //JPA Audit과 비슷하게 @createdBy 자동 생성 등의 역할을 함
public class R2dbcTemplate extends AbstractR2dbcConfiguration{

    @Value("spring.r2dbc.url")
    private String url;

    @Override //database와 연결시킬 정보들
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = ConnectionFactories.get(url);
        return connectionFactory;
    }
    
    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory databaseClient) {
        return new R2dbcEntityTemplate(databaseClient);
    }

    /*
     * R2dbcMappingContext의 역할 : 1. 엔티티 매핑 정보 관리, 식별자 관리, 엔티티 메타데이터 제공, 데이터베이스 연결 설정 관리.
     */
    @Bean
    public R2dbcMappingContext r2dbcMappingContext(DatabaseClient databaseClient) {
        R2dbcMappingContext mappingContext = new R2dbcMappingContext();
        return mappingContext;
    }

    @Bean
    public PersistentEntitiesFactoryBean persistentEntitiesFactoryBean(R2dbcMappingContext mappingContext) {
        PersistentEntitiesFactoryBean factoryBean = new PersistentEntitiesFactoryBean(mappingContext);
        return factoryBean;
    }

}
