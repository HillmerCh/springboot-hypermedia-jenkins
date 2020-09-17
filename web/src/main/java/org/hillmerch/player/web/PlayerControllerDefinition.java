package org.hillmerch.player.web;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.hillmerch.player.client.dto.PlayerDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "players", description = "Controller for players")
public interface PlayerControllerDefinition {


	@Operation(
			summary = "Gets all players in the system",
			description = "Searches all players, the result is paged",
			operationId = "getAllPlayers",
			parameters = {

				@Parameter(name = "pageNumber", in = ParameterIn.QUERY,
					schema = @Schema(type = "integer", defaultValue = "0", example = "0"),
					description = "Page number. Notice that the first page starts with index 0."),
				@Parameter(name = "pageSize", in = ParameterIn.QUERY,
						schema = @Schema(type = "integer", defaultValue = "10", example = "10"), description = "Page size"),
				@Parameter(name = "sortKey", in = ParameterIn.QUERY, array = @ArraySchema(schema = @Schema(type = "string")),
						description = "Sorting criteria in the format: property,asc|dec. "
								+ "Default sort order is ascending."
								+ " Multiple sort criteria are supported.",
						example = "id,desc,name,asc")
			})
	@GetMapping
	ResponseEntity<PagedModel<EntityModel<PlayerDTO>>> getAllPlayers(@RequestParam(value="pageNumber", defaultValue="0") Integer pageNumber,
																	 @RequestParam(value="pageSize", defaultValue="10") Integer pageSize,
																	 @RequestParam(value="sortKey", defaultValue="id,asc") String sortKey);


	@Operation(summary = "Gets players by uniform number",
			description = "Searches players by their uniform number, the result is paged",
			operationId = "getPlayersByUniformNumber")
	@GetMapping("/")
	ResponseEntity<CollectionModel<EntityModel<PlayerDTO>>> getPlayersByUniformNumber(
			@Parameter(description = "Player's uniform number to lookup for", required = true)
			@RequestParam("uniformNumber")
			@NotNull Long uniformNumber,
			@Parameter(description = "Player's uniform number LAST to lookup for", required = true)
			@RequestParam("uniformNumberLast")
			@NotNull Long uniformNumberLast);

	@Operation(summary = "Adds a player",
			description = "Adds a new player",
			operationId = "newPlayer")
	@PostMapping
	ResponseEntity<EntityModel<PlayerDTO>> newPlayer(@Valid @RequestBody PlayerDTO newPlayerDTO);

	@Operation(summary = "Gets the player identified by idPlayer",
			description = "Gets the player identified by idPlayer",
			operationId = "getPlayer")
	@GetMapping("/{idPlayer}")
	ResponseEntity<EntityModel<PlayerDTO>> getPlayer(@PathVariable Long idPlayer);

	@Operation(summary = "Replaces the player identified by idPlayer",
			description = "Replaces the player identified by idPlayer",
			operationId = "replacePlayer")
	@PutMapping("/{idPlayer}")
	ResponseEntity<EntityModel<PlayerDTO>> replacePlayer(@RequestBody PlayerDTO newPlayerDTO, @PathVariable Long idPlayer);


	@Operation(summary = "Deletes the player identified by idPlayer",
			description = "Deletes the player identified by idPlayer",
			operationId = "deletePlayer")
	@DeleteMapping("/{idPlayer}")
	ResponseEntity<Void> deletePlayer(@NotNull @PathVariable Long idPlayer);

}
