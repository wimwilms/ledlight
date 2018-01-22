package be.discobolus.led;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LedApplication {

	public static void main(String[] args) {
		SpringApplication.run(LedApplication.class, args);
	}
}
