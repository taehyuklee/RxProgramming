# Non-blocking Multidb (PostgreSQL & MongoDB)

[ ] yaml configuration

```yaml
spring:
r2dbc:
    url: r2dbc:postgresql://localhost:5432/postgres
    username: postgres
    password: ********
    pool:
    max-size: 100


data:
    mongodb:
    uri: mongodb://localhost:27017/
    database: RxTest

autoconfigure:
    exclude:
    - org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
    - org.springframework.boot.autoconfigure.data.r2dbc.R2dbcAutoConfiguration
    - org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration
    - org.springframework.boot.autoconfigure.data.r2dbc.R2dbcRepositoriesAutoConfiguration
    - org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
    - org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration
    - org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration
```