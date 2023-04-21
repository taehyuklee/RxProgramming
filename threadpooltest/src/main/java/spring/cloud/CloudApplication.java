package spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;
import reactor.blockhound.BlockHound;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class CloudApplication {
	public static void main(String[] args) throws Exception {
		// BlockHound.install();

		SpringApplication.run(CloudApplication.class, args);
	}

}
