package com.m2dfs.ApiMeteoMarine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiMeteoMarineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMeteoMarineApplication.class, args);
	}

}
