# About gatewayMsa Package

<p align="center">
<img src="https://user-images.githubusercontent.com/89365465/236496191-ea78d904-7165-4453-a7f9-d106f42a6443.jpg" width="100%" height="100%">
</p>

Implementation of the function of routing to the desired microservice through the API registered in the API gateway.

<BR><BR>


# About rxApis Package
 this package provides <b><span style="color:red">non-blocking restful APIs</span></b> code based on WebFlux consisting of three directories: <b>mongoRxApi</b>, <b>postgreRxApi</b>, <b>multidbRxApi</b>. and each sub-packages used reactiveDatabase library such as <b>r2dbc</b>, <b>reactive-mongoRepository</b>. The database configuration is described in the [ReactiveDatabase repository](https://github.com/taehyuklee/ReactiveDataBase.git) <a> </a>.

<br>

- [ ] <b>postgreRxApi</b> <br>
 &nbsp; Implementation of non-blocking restful APIs (basic CRUD) using r2dbc and postgresql database. r2dbc follows a different @Id generation principle than standard JPA, so certain precautions are necessary. <br><br>
 
    - [ ] dependencies
    ```groovy
    //r2dbc dependency
	implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'
	runtimeOnly 'org.postgresql:r2dbc-postgresql'
    ```
    <br>

    - [ ] yaml settings
    ```yaml
    spring:
        r2dbc:
            url: r2dbc:postgresql://localhost:5432/postgres
            username: postgres
            password: ********
            pool:
            max-size: 100
    ```


    <br><br>

- [ ] <b>mongoRxApi</b> <br>
&nbsp; Implementation of non-blocking restful APIs (basic CRUD) using reactive-mongodb. <br><br>

    - [ ] dependencies
    ```groovy
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb-reactive'
    ```
    <br>

    - [ ] yaml settings
    ```yaml
    spring:
        data:
            mongodb:
            uri: mongodb://localhost:27017/
            database: RxTest
    ```


    <br><br>

- [ ] <b>multidbRxApi</b> <br>
&nbsp; Postgresql and mongodb may be chosen based on the yaml configuration. Accordingly, all r2dbc- and reactiveMongo-related auto-config functions were disabled and set separately via Java package config.<br><br>

    - [ ] dependencies
    ```groovy
    //r2dbc dependency
	implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'
	runtimeOnly 'org.postgresql:r2dbc-postgresql'

	//mongodb & rxMongodb dependency
	implementation 'org.springframework.boot:spring-boot-starter-data-mongodb-reactive'
    ```

    <br>

    - [ ] yaml settings <br>
    - See the multidbRxApi sub package


<BR><BR>




# About threadpooltest Package
 Spring webFlux is based on Event-loop structure. Accordingly, it is a package that investigates thread pools other than netty threadPool and performs performance tests for each thread pool.
The performance test tool was Apache-bench, and for details, read the README.md inside the threadpooltest package.
