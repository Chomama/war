package com.ryan.war;

import com.ryan.war.player.query.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses= PlayerRepository.class)
public class WarApplication {
	public static void main(String[] args) {
		SpringApplication.run(WarApplication.class, args);
	}

}
