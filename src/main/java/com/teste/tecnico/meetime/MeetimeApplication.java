package com.teste.tecnico.meetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MeetimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetimeApplication.class, args);
	}

}
