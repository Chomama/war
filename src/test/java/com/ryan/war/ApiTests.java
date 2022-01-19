package com.ryan.war;

import com.ryan.war.api.WarController;
import com.ryan.war.game.WarGame;
import com.ryan.war.player.query.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(WarController.class)
public class ApiTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private WarGame warGame;

    @Test
    void getPlayer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/getPlayer")
                .queryParam("playerId", "playerOne"))
                .andExpect(status().isOk()
        );
    }

    @Test
    void getPlayerWins() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/getPlayerWins")
                .param("playerId", "playerOne"))
                .andExpect(status().isOk());
    }

    @Test
    void start() {

    }



}
