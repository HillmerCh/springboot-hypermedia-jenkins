package org.hillmerch.player.model.dao;

import java.util.List;
import java.util.Optional;

import org.hillmerch.player.model.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerDao{

	Page<Player> findAll(Pageable pageable);

	List<Player> findByUniformNumber(Long uniformNumber);

	Player save(Player player);

	Optional<Player> findById(Long idPlayer);

	void deleteById(Long idPlayer);


}
