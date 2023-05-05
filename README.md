for reactive MongoDB & PostgreSQL <br>

# About gatewayMsa Package

<p align="center">
<img src="https://user-images.githubusercontent.com/89365465/236496191-ea78d904-7165-4453-a7f9-d106f42a6443.jpg" width="80%" height="80%">
</p>

It implements the function of routing to the desired microservice through the API registered in the API gateway.

<BR><BR>


# About rxApis Package
 this package provides <b><span style="color:red">non-blocking restful APIs</span></b> code based on WebFlux consisting of three directories: <b>mongoRxApi</b>, <b>postgreRxApi</b>, <b>multidbRxApi</b>. and each sub-packages used reactiveDatabase library such as <b>r2dbc</b>, <b>reactive-mongoRepository</b>.

<BR><BR>




# About threadpooltest Package
 Spring webFlux is based on Event-loop structure. Accordingly, it is a package that investigates thread pools other than netty threadPool and performs performance tests for each thread pool.
The performance test tool was Apache-bench, and for details, read the README.md inside the threadpooltest package.