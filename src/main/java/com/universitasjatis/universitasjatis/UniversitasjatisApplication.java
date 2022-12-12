package com.universitasjatis.universitasjatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UniversitasjatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversitasjatisApplication.class, args);
	}

}
