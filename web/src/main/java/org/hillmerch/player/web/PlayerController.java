package org.hillmerch.player.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hillmerch.player.client.dto.PlayerDTO;
import org.hillmerch.player.web.assemblers.PlayerAssembler;
import org.hillmerch.player.web.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController implements PlayerControllerDefinition {

	private static final Logger logger = LoggerFactory.getLogger( PlayerController.class );

	private final PlayerService playerService;
	private final PlayerAssembler playerAssembler;
	private final PagedResourcesAssembler<PlayerDTO> pagedResourcesAssembler;

	PlayerController(PlayerService playerService, PlayerAssembler playerAssembler, PagedResourcesAssembler<PlayerDTO> pagedResourcesAssembler) {
		this.playerService = playerService;
		this.playerAssembler = playerAssembler;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
	}


	@Override
	public ResponseEntity<PagedModel<EntityModel<PlayerDTO>>> getAllPlayers(Integer pageNumber,
																			Integer pageSize,
																			String sortKey) {
		logger.info( "Searching all players" );

		List<Sort.Order> orders = new ArrayList<>();

		String[] sortArray = sortKey.split( "," );
		for(int it=0; it < sortArray.length; it=it+2){
			orders.add(new Sort.Order(Sort.Direction.valueOf( sortArray[it+1].toUpperCase() ) , sortArray[it]));
		}

		Pageable pageable = PageRequest.of( pageNumber, pageSize, Sort.by( orders));

		Page<PlayerDTO> entityModelList = this.playerService.findAll( pageable );
		return ResponseEntity.status( HttpStatus.OK ).body( pagedResourcesAssembler.toModel( entityModelList, playerAssembler ) );
	}

	@Override
	public ResponseEntity<CollectionModel<EntityModel<PlayerDTO>>> getPlayersByUniformNumber(
			Long uniformNumber,
			Long uniformNumberLast) {

		logger.info( "Searching players by uniformNumber: " + uniformNumber );
		List<PlayerDTO> list = playerService.findByUniformNumber( uniformNumber );

		/*
		To define link instead of use the playerAssembler defined
		CollectionModel<PlayerDTO> playerDTOCollectionModel = CollectionModel.of( list, linkTo( methodOn( PlayerController.class ).getPlayer( 1L ) ).withSelfRel(),
															   linkTo( methodOn( PlayerController.class ).getAllPlayers(0, 10, "id") ).withRel( "players" )
		);
		 */

		return ResponseEntity.status( HttpStatus.OK ).body( playerAssembler.toCollectionModel( list ) ) ;

	}

	@Override
	public ResponseEntity<EntityModel<PlayerDTO>> newPlayer(PlayerDTO newPlayerDTO) {
		logger.info( "Adding player: " + newPlayerDTO );
		PlayerDTO playerDTO = playerService.save( newPlayerDTO );

		//3 different ways to generate the hypermedia

		/*
		//1. To add links instead of use the playerAssembler defined
		EntityModel<PlayerDTO> playerDTOEntityModel = EntityModel.of( playerDTO );
		playerDTOEntityModel.add( linkTo( methodOn( PlayerController.class ).getPlayer( playerDTO.getId() ) ).withSelfRel() );*/

		/*
		//2. To define links instead of use the playerAssembler defined
		EntityModel<PlayerDTO> playerDTOEntityModel = EntityModel.of(
				playerDTO,
				linkTo( methodOn( PlayerController.class ).getPlayer( playerDTO.getId() ) ).withSelfRel()
		);*/

		//3. To define links using the playerAssembler defined
		EntityModel<PlayerDTO> playerDTOEntityModel = playerAssembler.toModel( playerDTO );
		return ResponseEntity.status( HttpStatus.OK ).body( playerDTOEntityModel );
	}

	@Override
	public ResponseEntity<EntityModel<PlayerDTO>> getPlayer(Long idPlayer) {
		logger.info( "Getting player identified by: " + idPlayer );
		PlayerDTO playerDTO = playerService.findById( idPlayer );
		return ResponseEntity.status( HttpStatus.OK ).body( playerAssembler.toModel( playerDTO ) );
	}

	@Override
	public ResponseEntity<EntityModel<PlayerDTO>> replacePlayer(PlayerDTO newPlayerDTO, Long idPlayer) {
		logger.info( "Updating player identified by: " + idPlayer );
		PlayerDTO playerDTO = this.playerService.replacePlayer( newPlayerDTO, idPlayer );
		return ResponseEntity.status( HttpStatus.OK ).body( playerAssembler.toModel( playerDTO ) );
	}

	@Override
	public ResponseEntity<Void> deletePlayer(Long idPlayer) {
		logger.info( "Deleting player identified by: " + idPlayer );
		playerService.deleteById( idPlayer );
		return ResponseEntity.status( HttpStatus.NO_CONTENT ).build();
	}

}
