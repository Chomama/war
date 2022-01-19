package com.ryan.war;

import com.ryan.war.api.WarController;
import com.ryan.war.game.WarGame;
import com.ryan.war.player.query.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class WarApplicationTests {

	@Autowired
	private PlayerRepository repository;

	@Autowired
	private WarController controller;

	@Autowired
	private WarGame game;

	@Test
	void contextLoads() {
		assertThat(repository).isNotNull();
		assertThat(controller).isNotNull();
		assertThat(game).isNotNull();

	}

}
