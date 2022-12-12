package com.socialnetwork.consumerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerRMQServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerRMQServiceApplication.class, args);
	}

}
