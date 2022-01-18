package com.ryan.war.api;

import com.ryan.war.game.WarGame;
import com.ryan.war.player.Player;
import com.ryan.war.player.query.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class WarController{

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private WarGame warGame;

    @GetMapping(value = "/start")
    public @ResponseBody String startGame() throws Exception {
        Player playerOne = repository.findByPlayerId("playerOne");
        Player playerTwo = repository.findByPlayerId("playerTwo");
        Player winner = warGame.startGame(playerOne, playerTwo);
        repository.save(winner);
        return "Game has started and is running!";
    }

    @GetMapping(value = "/getPlayer")
    public @ResponseBody Player getPlayer(String playerId) {
        return repository.findByPlayerId(playerId);
    }

    @GetMapping(value = "/getPlayerWins")
    public @ResponseBody int getPlayerWins(String playerId) {
        Player player =  repository.findByPlayerId(playerId);
        return player.getWins();
    }



}
    