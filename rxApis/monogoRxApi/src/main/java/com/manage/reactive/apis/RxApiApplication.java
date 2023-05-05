package com.manage.reactive.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableReactiveMongoAuditing
public class RxApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RxApiApplication.class, args);
	}

}
