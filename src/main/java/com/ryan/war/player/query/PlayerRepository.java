package com.ryan.war.player.query;

import com.ryan.war.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Player findByPlayerId(String playerId);
}
