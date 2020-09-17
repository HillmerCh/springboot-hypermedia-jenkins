package org.hillmerch.player.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hillmerch.player.model.dao.PlayerDao;
import org.hillmerch.player.model.model.Player;
import org.hillmerch.player.client.dto.PlayerDTO;
import org.hillmerch.player.web.exception.PlayerNotFoundException;
import org.hillmerch.player.web.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

	private final PlayerDao playerDao;
	private final ModelMapper modelMapper;


	public PlayerServiceImpl(PlayerDao playerDao, ModelMapper modelMapper) {
		this.playerDao = playerDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public Page<PlayerDTO> findAll(Pageable pageable){
		Page<Player> playerPage = this.playerDao.findAll(pageable);

		List<PlayerDTO> playerDTODtoList = playerPage.getContent()
				.stream()
				.map(p -> modelMapper.map(p, PlayerDTO.class))
				.collect(Collectors.toList());

		return new PageImpl<>( playerDTODtoList, pageable, playerPage.getTotalElements());
	}

	@Override
	public List<PlayerDTO> findByUniformNumber(Long uniformNumber){
		List<Player> playerList = this.playerDao.findByUniformNumber(uniformNumber);
		return playerList.stream().map( p-> modelMapper.map(p, PlayerDTO.class) ).collect( Collectors.toList());
	}

	@Override
	public PlayerDTO save(PlayerDTO newPlayerDTO){

		Player player = modelMapper.map(newPlayerDTO, Player.class);

		player = this.playerDao.save( player );

		return modelMapper.map(player, PlayerDTO.class);
	}
	@Override
	public PlayerDTO findById(Long idPlayer){
		Player player = this.playerDao.findById( idPlayer ).orElseThrow(() -> new PlayerNotFoundException( idPlayer));

		return modelMapper.map(player, PlayerDTO.class);
	}
	@Override
	public PlayerDTO replacePlayer(PlayerDTO newPlayerDTO, Long idPlayer) {

		Player player = this.playerDao.findById( idPlayer ).orElseThrow(() -> new PlayerNotFoundException( idPlayer ));

		/*
		If not all entity attributes are available in the DTO, it could be desirable to set their values instead of using then ModelMapper
		player.setName( newPlayerDTO.getName() );
		player.setPhoneNumber( newPlayerDTO.getPhoneNumber() );
		player.setPosition( Player.Position.valueOf( newPlayerDTO.getPosition() ) );
		player.setTeam( newPlayerDTO.getTeam() );*/

		modelMapper.map( newPlayerDTO,  player);
		player.setId( idPlayer );

		player = this.playerDao.save( player );

		return modelMapper.map(player, PlayerDTO.class);
	}
	@Override
	public void deleteById(Long idPlayer){
		Player player = this.playerDao.findById( idPlayer ).orElseThrow(() -> new PlayerNotFoundException( idPlayer));
		this.playerDao.deleteById( player.getId() );
	}
}
