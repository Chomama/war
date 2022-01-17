package com.ryan.war.game.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Player findByPlayerId(String playerId);
}
