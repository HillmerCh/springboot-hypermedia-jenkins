package org.hillmerch.player.web.assemblers;

import org.hillmerch.player.web.PlayerController;
import org.hillmerch.player.client.dto.PlayerDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlayerAssembler implements RepresentationModelAssembler<PlayerDTO, EntityModel<PlayerDTO>> {


	@Override
	public EntityModel<PlayerDTO> toModel(PlayerDTO playerDTO) {
		return EntityModel.of (playerDTO,
								 linkTo(methodOn( PlayerController.class).getPlayer( playerDTO.getId())).withSelfRel(),
								 linkTo(methodOn( PlayerController.class).getAllPlayers(0, 10, "id,asc")).withRel( "players"));
	}
}
