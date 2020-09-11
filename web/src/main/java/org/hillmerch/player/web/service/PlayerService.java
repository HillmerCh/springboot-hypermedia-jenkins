package org.hillmerch.player.web.service;

import java.util.List;

import org.hillmerch.player.client.dto.PlayerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

	 Page<PlayerDTO> findAll(Pageable pageable);

	 List<PlayerDTO> findByUniformNumber(Long uniformNumber);

	 PlayerDTO save(PlayerDTO newPlayerDTO);

	 PlayerDTO findById(Long idPlayer);

	 PlayerDTO replacePlayer(PlayerDTO newPlayerDTO, Long idPlayer);

	 void deleteById(Long idPlayer);
}
