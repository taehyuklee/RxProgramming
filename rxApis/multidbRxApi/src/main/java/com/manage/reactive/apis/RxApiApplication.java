package com.manage.reactive.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = "com.manage.reactive.apis.personapis.domain.repository")
@EnableR2dbcAuditing //JPA Audit과 비슷하게 @createdBy 자동 생성 등의 역할을 함
public class RxApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RxApiApplication.class, args);
	}

}
