package org.hillmerch.player.web;

import java.util.List;
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
			}/*,
			responses = {
			@ApiResponse(responseCode = "200", description = "OK", links = {
					@Link(operationId = "getPlayer", parameters = {
							@LinkParameter(name = "userId", expression = "$request.query.idPlayer")
					}),
					@Link(operationId = "getAllPlayers", parameters = {
							@LinkParameter(name = "pageNumber"),
							@LinkParameter(name = "pageSize"),
							@LinkParameter(name = "sortKey")
					})
			}
			),
			@ApiResponse(responseCode = "400", description = "bad request")
	}*/)
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
			operationId = "newPlayer"/*,
			responses = {
			@ApiResponse(responseCode = "200", description = "OK", links = {
					@Link(name = "Download Avatar", operationId = "downloadAvatar", parameters = {
							@LinkParameter(name = "userId", expression = "$request.query.userId")
					})
			}
			)
	}*/)
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

	/*
	@Operation(description = "adds an inventory item", operationId = "addInventory", summary = "Adds an item to the system", tags = {
			"admins", })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "item created"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid"),
			@ApiResponse(responseCode = "409", description = "an existing item already exists") })
	@PostMapping(value = "/inventory", consumes = { "application/json" })
	ResponseEntity<Void> addInventory(
			@Parameter(description = "Inventory item to do") @Valid @RequestBody InventoryItem body);

			@Operation(description = "searches inventory", operationId = "searchInventory", summary = "By passing in the appropriate options, you can search for available inventory in the system ", tags = {
			"developers", })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "search results matching criteria"),
			@ApiResponse(responseCode = "400", description = "bad input parameter") })
	@GetMapping(value = "/inventory", produces = { "application/json" })
	ResponseEntity<List<InventoryItem>> searchInventory(
			@Valid @RequestParam(value = "searchString", required = false) String searchString,
			@Min(0) @Parameter(description = "number of records to skip for pagination") @Valid @RequestParam(value = "skip", required = true) Integer skip,
			@Min(0) @Max(50) @Parameter(description = "maximum number of records to return") @Valid @RequestParam(value = "limit", required = true) Integer limit);

	*/

	/*@Operation(summary = "Request avatar info", operationId = "requestAvatar", responses = {
			@ApiResponse(responseCode = "200", description = "OK", links = {
					@Link(name = "Download Avatar", operationId = "downloadAvatar", parameters = {
							@LinkParameter(name = "userId", expression = "$request.query.userId"),
							@LinkParameter(name = "uuid", expression = "$request.query.uuid")
					})
			}
			)
	})*/


	/*@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "search all players",
			links = {
			@Link(name = "Download Avatar1", operationId = "downloadAvatar1", parameters = {
					@LinkParameter(name = "userId", expression = "$request.query.userId"),
					@LinkParameter(name = "uuid", expression = "$request.query.uuid")
			})
	}
	),
			@ApiResponse(responseCode = "400", description = "bad request") })*/



	/*
	@Operation(
            security = {@SecurityRequirement(name = "authZ")},
            summary = "searches materials on material supplier level",
            description = "By passing in the appropriate options, you can search for available materials on material supplier level in the system",
            method = "GET",
            parameters = {
                    @Parameter(name = "page", in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer", defaultValue = "0", example = "0"),
                            description = "Page number. Notice that the first page starts with index 0."),
                    @Parameter(name = "size", in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer", defaultValue = "100", example = "10"), description = "Page size"),
                    @Parameter(name = "sort", in = ParameterIn.QUERY, array = @ArraySchema(schema = @Schema(type = "string")),
                            description = "Sorting criteria in the format: property,asc|dec. "
                                    + "Default sort order is ascending."
                                    + "Multiple sort criteria are supported.",
                            example = "name,desc")}
	* */

	/*

	@Parameter(hidden = true) @PageableDefault(size = 50)
                                                                          @SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
                                                                          @Parameter(hidden = true) MaterialCriteria criteria

	cuando se usa un DTO para recibir muchos parámetros
	* */

	/*@Parameter(hidden = true) @PageableDefault(size = 50)
	@SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
	@Parameter(hidden = true) MaterialCriteriaLewis criteria //OCULTAR ALGO VISUAL*/
}
