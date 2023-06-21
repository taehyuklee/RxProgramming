package spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class CloudApplication {
	public static void main(String[] args) throws Exception {
		// BlockHound.install();
		log.info("WebFlux ThreadPool Test App이 시작되었습니다.");
		SpringApplication.run(CloudApplication.class, args);
	}

}
