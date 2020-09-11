package org.hillmerch.player.model.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hillmerch.player.model.dao.PlayerDao;
import org.hillmerch.player.model.model.Player;
import org.hillmerch.player.model.repository.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayerDaoImpl implements PlayerDao {

	final PlayerRepository playerRepository;


	public PlayerDaoImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Override
	public Page<Player> findAll(Pageable pageable) {
		return this.playerRepository.findAll(pageable);
	}

	@Override
	public List<Player> findByUniformNumber(Long uniformNumber) {
		return playerRepository.findByUniformNumber(uniformNumber);
	}

	@Override
	public Player save(Player player) {
		return this.playerRepository.save( player );
	}

	@Override
	public Optional<Player> findById(Long idPlayer) {
		return this.playerRepository.findById( idPlayer );
	}

	@Override
	public void deleteById(Long idPlayer) {
		this.playerRepository.deleteById( idPlayer );
	}
}
