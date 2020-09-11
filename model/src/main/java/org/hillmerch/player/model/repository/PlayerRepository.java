package org.hillmerch.player.model.repository;

import java.util.List;

import org.hillmerch.player.model.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	List<Player> findByUniformNumber(Long uniformNumber);
}
