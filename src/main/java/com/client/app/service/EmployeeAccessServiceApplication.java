package com.client.app.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.client.app.service.repository.EmployeeRepositoryAccess;
import com.client.app.service.repository.EmployeeRepositoryAccessImpl;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages="client.app.service")
public class EmployeeAccessServiceApplication {
	
	public static final String EMPLOYEE_DATA_SERVICE_URL = "http://employee-microservice-dataset";
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeAccessServiceApplication.class, args);
	}
	
	@Bean
	public EmployeeRepositoryAccess profileRepository(){
		return new EmployeeRepositoryAccessImpl(EMPLOYEE_DATA_SERVICE_URL);
	}
}
