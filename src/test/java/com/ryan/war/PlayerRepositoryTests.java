package com.ryan.war;

import com.ryan.war.player.Player;
import com.ryan.war.player.query.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class PlayerRepositoryTests {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void savePlayer() {
        Player testPlayer = new Player("testPlayer", 0, null, null);
        Player savedPlayer = playerRepository.save(testPlayer);
        assertThat(savedPlayer).isNotNull();
    }

    @Test
    public void getAllPLayers() {
        List<Player> players = playerRepository.findAll();
        assertThat(players).hasSize(2);
    }

    @Test
    public void getPlayerById() {
        Player player = playerRepository.findByPlayerId("playerOne");
        assertThat(player.getPlayerId()).isEqualTo("playerOne");
    }



}
