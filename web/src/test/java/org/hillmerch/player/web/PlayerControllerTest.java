package org.hillmerch.player.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.hillmerch.player.client.handler.ErrorMessage;
import org.hillmerch.player.client.dto.PlayerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
* This Test Case class is deprecated since a new class PlayerControllerWithMockMvcTest was created.
* */
@Deprecated
@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String restUrl;

	@BeforeEach
	public void setup() {
		restUrl = "http://localhost:" + port + "/players";
	}

	@Test
	@DisplayName("Getting all Players")
	void getAllPlayers() {

		ResponseEntity response2 = restTemplate.getForEntity( restUrl, Object.class );
		System.out.println(response2.getHeaders());

		ResponseEntity<PagedModel> response = restTemplate.getForEntity( restUrl, PagedModel.class );

		assertEquals( HttpStatus.OK, response.getStatusCode() );

		ModelMapper modelMapper = new ModelMapper();
		List<PlayerDTO> personResponseDtoList = (List<PlayerDTO>) response.getBody().getContent()
				.stream()
				.map(p -> modelMapper.map(p, PlayerDTO.class))
				.collect(Collectors.toList());

		assertTrue( personResponseDtoList.size() > 0 );
	}

	@Test
	@DisplayName("Getting all players by uniform number from 3 to 9")
	void getPlayersByUniformNumber() {
		ResponseEntity<CollectionModel> response = restTemplate.getForEntity( restUrl + "/?uniformNumber=3&uniformNumberLast=9", CollectionModel.class );

		assertEquals( HttpStatus.OK, response.getStatusCode() );

		ModelMapper modelMapper = new ModelMapper();
		List<PlayerDTO> personResponseDtoList = (List<PlayerDTO>) response.getBody().getContent()
				.stream()
				.map(p -> modelMapper.map(p, PlayerDTO.class))
				.collect(Collectors.toList());

		assertTrue( personResponseDtoList.size() > 0 );
	}

	@Test
	@DisplayName("Posting a new Player")
	void addPlayer() {
		ResponseEntity<Object> response = restTemplate.postForEntity( restUrl,
																	  new PlayerDTO()
																			  .name( "Mike Trout" )
																			  .uniformNumber( 27L )
																			  .phoneNumber( "1" )
																			  .position( "OUTFIELD" )
																			  .team( "Angels" )
																		, Object.class);
		assertEquals( HttpStatus.OK, response.getStatusCode() );
	}

	@Test
	@DisplayName("Getting a BAD_REQUEST when Posting an empty Player")
	void givenANewPlayerWithoutVales_whenRunNewPlayer_thenGetABadRequest() {

		List<String> errorMessageList = new ArrayList<>(List.of( "Invalid property phoneNumber: must not be blank",
																 "Invalid property position: Position must not be blank",
																 "Invalid property name: must not be blank",
																 "Invalid property uniformNumber: must not be null",
																 "Invalid property team: must not be blank"));

		ResponseEntity<ErrorMessage> response = restTemplate.postForEntity( restUrl,
																			new PlayerDTO(),
																			ErrorMessage.class
		);

		assertEquals( HttpStatus.BAD_REQUEST, response.getStatusCode() );
		assertEquals( 5, response.getBody().getErrorMessageList().size() );

		errorMessageList.removeAll( response.getBody().getErrorMessageList().stream().map( p->p.getMessage() ).collect( Collectors.toList()) );
		assertEquals( 0, errorMessageList.size() );
	}

	@Test
	@DisplayName("Getting a Player by id= 1")
	void getPlayer() {
		ResponseEntity<PlayerDTO> response = restTemplate.getForEntity( restUrl + "/1", PlayerDTO.class );
		assertEquals( HttpStatus.OK, response.getStatusCode() );
		assertEquals( 1L, response.getBody().getId() );
	}

	@Test
	@DisplayName("Getting a NOT_FOUND when try to get a Player identified by -1")
	void givenAPlayerId_whenRunGetPlayer_thenGetANotFound() {
		ResponseEntity<String> response = restTemplate.getForEntity( restUrl + "/-1", String.class );
		assertEquals( HttpStatus.NOT_FOUND, response.getStatusCode() );
	}

	@Test
	@DisplayName("Putting a Player identified by 5")
	void replacePlayer() {
		String resourceUrl = restUrl + "/1";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<PlayerDTO> requestUpdate = new HttpEntity<>( new PlayerDTO()
																		.name( "Hillmer Ch" )
																		.uniformNumber( 5L )
																		.phoneNumber( "1" )
																		.position( "OUTFIELD" )
																		.team( "MY TEAM" ),
																headers );

		ResponseEntity<PlayerDTO> response = restTemplate.exchange( resourceUrl, HttpMethod.PUT, requestUpdate, PlayerDTO.class );

		assertEquals( HttpStatus.OK, response.getStatusCode() );
		assertEquals( "Hillmer Ch", response.getBody().getName() );
	}

	@Test
	@DisplayName("Getting a NOT_FOUND when try to put a Player identified by -1")
	void givenAPlayerIdentifiedBy1_whenRunPutPlayer_thenGetANotFound() {
		String resourceUrl = restUrl + "/-1";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<PlayerDTO> requestUpdate = new HttpEntity( new PlayerDTO( "Hillmer Ch", "OUTFIELD", 1L, "123", "MY TEAM" ), headers );
		ResponseEntity<ErrorMessage> response = restTemplate.exchange( resourceUrl, HttpMethod.PUT, requestUpdate, ErrorMessage.class );
		assertEquals( HttpStatus.NOT_FOUND, response.getStatusCode() );
		assertEquals( "Could not find player -1", response.getBody().getMessage() );
	}

	@Test
	@DisplayName("Deleting a Player identified by 2")
	void deletePlayer() {
		restTemplate.delete( restUrl + "/2" );
	}

	@Test
	@DisplayName("Getting a NOT_FOUND when try to delete a Player identified by -1")
	void givenAPlayerIdentifiedBy1_whenRunDeletePlayer_thenGetANotFound() {
		String resourceUrl = restUrl + "/-1";
		restTemplate.delete( restUrl + "/2" );

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<PlayerDTO> requestUpdate = new HttpEntity( new PlayerDTO( "Hillmer Ch", "OUTFIELD", 1L, "123", "MY TEAM" ), headers );
		ResponseEntity<ErrorMessage> response = restTemplate.exchange( resourceUrl, HttpMethod.DELETE, requestUpdate, ErrorMessage.class );
		assertEquals( HttpStatus.NOT_FOUND, response.getStatusCode() );
		assertEquals( "Could not find player -1", response.getBody().getMessage() );
	}
}