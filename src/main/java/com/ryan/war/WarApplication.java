package com.ryan.war;

import com.ryan.war.game.WarGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.Queue;

@SpringBootApplication
public class WarApplication {

	public static void main(String[] args) {
//		SpringApplication.run(WarApplication.class, args);
		WarGame game = new WarGame();
		game.startGame();
//		game.playRound();
	}

}
