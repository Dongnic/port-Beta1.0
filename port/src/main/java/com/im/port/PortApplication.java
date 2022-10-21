package com.im.port;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.im.port.config.security.oauth.jwt.AppProperties;

@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class PortApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortApplication.class, args);
	}

}
