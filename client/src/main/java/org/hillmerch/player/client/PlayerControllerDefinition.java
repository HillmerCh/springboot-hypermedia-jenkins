package org.hillmerch.player.client;

import java.util.List;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.hillmerch.player.client.dto.PlayerDTO;
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

public interface PlayerControllerDefinition {
	@Operation(description = "searches all players", operationId = "all", summary = "You can get all players in the system ", tags = {
			"players"
	}
			, responses = {
			@ApiResponse(responseCode = "200", description = "OK", links = {
					@Link(name = "Download Player", operationId = "downloadPlayer", parameters = {
							@LinkParameter(name = "userId", expression = "$request.query.userId")
					})
			}
			),
			@ApiResponse(responseCode = "400", description = "bad request")
	})
	@GetMapping
	ResponseEntity<PagedModel<EntityModel<PlayerDTO>>> all();

	@Operation(summary = "getByUniformNumber", description = "Searching players by uniformNumber")
	@GetMapping("/")
	List<PlayerDTO> getByUniformNumber(
			@Parameter(description = "Player's uniform number to lookup for", required = true)
			@RequestParam("uniformNumber") Long uniformNumber,
			@Parameter(description = "Player's uniform number LAST to lookup for", required = true)
			@RequestParam("uniformNumberLast") Long uniformNumberLast);

	@Operation(summary = "Adding player", operationId = "newPlayer", responses = {
			@ApiResponse(responseCode = "200", description = "OK", links = {
					@Link(name = "Download Avatar", operationId = "downloadAvatar", parameters = {
							@LinkParameter(name = "userId", expression = "$request.query.userId")
					})
			}
			)
	})
	@PostMapping
	PlayerDTO newPlayer(@Valid @RequestBody PlayerDTO newPlayerDTO);

	@Operation(summary = "one", description = "Getting player identified by")
	@GetMapping("/{idPlayer}")
	PlayerDTO one(@PathVariable Long idPlayer);

	@Operation(summary = "replacePlayer", description = "Updating player identified by")
	@PutMapping("/{idPlayer}")
	PlayerDTO replacePlayer(@RequestBody PlayerDTO newPlayerDTO, @PathVariable Long idPlayer);

	@Operation(summary = "deletePlayer", description = "Deleting player identified by")
	@DeleteMapping("/{idPlayer}")
	void deletePlayer(@PathVariable Long idPlayer);
}
