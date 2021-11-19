package com.m2dfs.ApiRestMeteo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication

public class ApiRestMeteoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestMeteoApplication.class, args);
	}

}
