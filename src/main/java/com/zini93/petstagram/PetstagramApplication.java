package com.zini93.petstagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PetstagramApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetstagramApplication.class, args);
	}

}
