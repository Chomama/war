package com.ryan.war.api;

import com.ryan.war.game.WarGame;
import com.ryan.war.game.player.Player;
import com.ryan.war.game.player.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class WarController{

    @Autowired
    private PlayerRepository repository;

    //findPlayerTest

    @GetMapping(value = "/getPlayer")
    public @ResponseBody Player getPlayer(String playerId) {
        return repository.findByPlayerId(playerId);
    }

    @GetMapping(value = "/getPlayerWins")
    public @ResponseBody int getPlayerWins(String playerId) {
        Player player =  repository.findByPlayerId(playerId);
        return player.getWins();
    }


    //start game
    @GetMapping(value = "/start")
    public @ResponseBody void startGame() {
        Player playerOne = repository.findByPlayerId("playerOne");
        Player playerTwo = repository.findByPlayerId("playerTwo");
        WarGame game = new WarGame();
        game.startGame(playerOne, playerTwo);
    }

    //getWins


}
    