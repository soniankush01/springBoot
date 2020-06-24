package com.excercise.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtsApplication.class, args);
	}

}
