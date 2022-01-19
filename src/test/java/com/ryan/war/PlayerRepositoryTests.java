package com.ryan.war;

import com.ryan.war.player.Player;
import com.ryan.war.player.query.PlayerRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
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
