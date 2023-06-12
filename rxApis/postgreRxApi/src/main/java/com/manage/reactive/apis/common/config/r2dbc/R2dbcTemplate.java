package com.manage.reactive.apis.common.config.r2dbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.transaction.TransactionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.PersistentEntitiesFactoryBean;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.AbstractTransactionManagementConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionalOperator;

import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableConfigurationProperties(TransactionProperties.class)
@EnableTransactionManagement
public class R2dbcTemplate extends AbstractR2dbcConfiguration{

    /*https://steady-coding.tistory.com/640 */

    @Value("${spring.r2dbc.url}")
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

    // @Bean
    // public Connection conenction(){
    //     return new PostgresqlConnectionFactory(null);
    // }


    // @Bean /* 이게 database와 연결시키는 정보가 되기도 한다 (이부분 알아서 가져와지는 부분) */
    // public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
    //     return DatabaseClient.builder().connectionFactory(connectionFactory).build();
    // }

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
    
    /*---------------- Transaction 관련 설정들 ----------------- */
    @Bean(name = "r2dbcTransactionManager")
    public ReactiveTransactionManager reactiveTransactionManager(ConnectionFactory ConnectionFactory){
        return new R2dbcTransactionManager(connectionFactory());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnSingleCandidate(ReactiveTransactionManager.class)
    public TransactionalOperator transactionalOperator(ReactiveTransactionManager transactionManager) {
        return TransactionalOperator.create(transactionManager);
    }

     @Configuration(proxyBeanMethods = false)
    @ConditionalOnBean(TransactionManager.class)
    @ConditionalOnMissingBean(AbstractTransactionManagementConfiguration.class)
    public static class EnableTransactionManagementConfiguration {

        @Configuration(proxyBeanMethods = false)
        @EnableTransactionManagement(proxyTargetClass = false)
        @ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "false")
        public static class JdkDynamicAutoProxyConfiguration {

        }

        @Configuration(proxyBeanMethods = false)
        @EnableTransactionManagement(proxyTargetClass = true)
        @ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "true",
                matchIfMissing = true)
        public static class CglibAutoProxyConfiguration {

        }

    }


}
