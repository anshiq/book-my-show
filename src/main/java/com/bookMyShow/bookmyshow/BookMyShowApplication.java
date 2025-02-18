package com.bookMyShow.bookmyshow;

import com.example.configclient.ConfigClient;
import com.example.configclient.ConfigService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.UUID;

@SpringBootApplication(scanBasePackageClasses = {BookMyShowApplication.class, ConfigService.class})
@EnableFeignClients(basePackageClasses = {ConfigClient.class})
@EnableCaching
@EnableScheduling
public class BookMyShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

	String s = UUID.randomUUID().toString();



}
