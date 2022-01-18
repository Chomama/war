package com.ryan.war.player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Player findByPlayerId(String playerId);
}
