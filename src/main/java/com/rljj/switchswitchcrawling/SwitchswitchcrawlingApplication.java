package com.rljj.switchswitchcrawling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SwitchswitchcrawlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwitchswitchcrawlingApplication.class, args);
	}

	@Scheduled(fixedRate = 5000)
	public void performTask() {
		System.out.println("Scheduled Task");
	}
}
