package org.hillmerch.player.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hillmerch.player.client.dto.PlayerDTO;
import org.hillmerch.player.client.handler.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class PlayerControllerWithMockMvcTest {

	@Autowired
	private MockMvc mockMvc;

	private final static String TEST_USER_ID = "user-id-123";

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		objectMapper = new  ObjectMapper();
		objectMapper.registerModule(new Jackson2HalModule());
	}

	@Test
	@DisplayName("Getting all Players")
	void getAllPlayers() throws Exception {

		MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders.get( "/players" )
													   .with( user( TEST_USER_ID ) )//Pretend we have a valid token
													   .with( csrf() )//Pretend we have a valid token
		)
				.andExpect( status().isOk() )
				.andReturn();

		PagedModel<List<PlayerDTO>> pagedModel = objectMapper.readValue( mvcResult.getResponse().getContentAsString(), PagedModel.class);

		assertTrue( pagedModel.getContent().size() > 0 );
	}

	@Test
	@DisplayName("Getting all players by uniform number from 3 to 9")
	void getPlayersByUniformNumber() throws Exception {
		MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders.get( "/players/?uniformNumber=3&uniformNumberLast=9" )
													   .with( user( TEST_USER_ID ) )//Pretend we have a valid token
													   .with( csrf() )//Pretend we have a valid token
		).andExpect( status().isOk() )
				.andReturn();

		PagedModel<List<PlayerDTO>> pagedModel = objectMapper.readValue( mvcResult.getResponse().getContentAsString(), PagedModel.class);

		assertTrue( pagedModel.getContent().size() > 0 );
	}

	@Test
	@DisplayName("Posting a new Player")
	void addPlayer() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString( new PlayerDTO()
																.name( "Mike Trout" )
																.uniformNumber( 27L )
																.phoneNumber( "1" )
																.position( "OUTFIELD" )
																.team( "Angels" ) );

		mockMvc.perform( MockMvcRequestBuilders.post( "/players" )
								 .content( jsonContent )
								 .contentType( MediaType.APPLICATION_JSON )
								 .with( user( TEST_USER_ID ) )//Pretend we have a valid token
								 .with( csrf() )//Pretend we have a valid token
		).andExpect( status().isOk() );

	}

	@Test
	@DisplayName("Getting a BAD_REQUEST when Posting an empty Player")
	void givenANewPlayerWithoutVales_whenRunNewPlayer_thenGetABadRequest() throws Exception {

		List<String> errorMessageList = new ArrayList<>( List.of(
				"Invalid property phoneNumber: must not be blank",
				"Invalid property position: Position must not be blank",
				"Invalid property name: must not be blank",
				"Invalid property uniformNumber: must not be null",
				"Invalid property team: must not be blank"
		) );

		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString( new PlayerDTO() );

		MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders.post( "/players" )
													   .content( jsonContent )
													   .contentType( MediaType.APPLICATION_JSON )
													   .with( user( TEST_USER_ID ) )//Pretend we have a valid token
													   .with( csrf() )//Pretend we have a valid token
		).andExpect( status().isBadRequest() )
				.andReturn();

		ErrorMessage errorMessage = objectMapper.readValue( mvcResult.getResponse().getContentAsString(), ErrorMessage.class );

		assertEquals( 5, errorMessage.getErrorMessageList().size() );

		errorMessageList.removeAll( errorMessage.getErrorMessageList().stream().map( p -> p.getMessage() ).collect( Collectors.toList() ) );
		assertEquals( 0, errorMessageList.size() );

	}

	@Test
	@DisplayName("Getting a Player by id= 1")
	void getPlayer() throws Exception {

		MvcResult mvcResult = mockMvc.perform( MockMvcRequestBuilders.get( "/players/1" )
													   .with( user( TEST_USER_ID ) )//Pretend we have a valid token
													   .with( csrf() )//Pretend we have a valid token

		).andExpect( status().isOk() )
				.andReturn();

		PlayerDTO playerDTO = objectMapper.readValue( mvcResult.getResponse().getContentAsString(), PlayerDTO.class );

		assertEquals( 1L, playerDTO.getId() );
	}

	@Test
	@DisplayName("Getting a NOT_FOUND when try to get a Player identified by -1")
	void givenAPlayerId_whenRunGetPlayer_thenGetANotFound() throws Exception {

		mockMvc.perform( MockMvcRequestBuilders.get( "/players/-1" )
								 .with( user( TEST_USER_ID ) )//Pretend we have a valid token
								 .with( csrf() )//Pretend we have a valid token

		).andExpect( status().isNotFound() );
	}

	@Test
	@DisplayName("Putting a Player identified by 5")
	void replacePlayer() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString( new PlayerDTO()
																.name( "Hillmer Ch" )
																.uniformNumber( 5L )
																.phoneNumber( "1" )
																.position( "OUTFIELD" )
																.team( "MY TEAM" ) );

		MvcResult mvcResult =
				mockMvc.perform( MockMvcRequestBuilders.put( "/players/1" )
										 .content( jsonContent )
										 .contentType( MediaType.APPLICATION_JSON )
										 .with( user( TEST_USER_ID ) )//Pretend we have a valid token
										 .with( csrf() )//Pretend we have a valid token

				).andExpect( status().isOk() )
						.andReturn();

		PlayerDTO playerDTOUpdated = objectMapper.readValue( mvcResult.getResponse().getContentAsString(), PlayerDTO.class );

		assertEquals( "Hillmer Ch", playerDTOUpdated.getName() );

	}

	@Test
	@DisplayName("Getting a NOT_FOUND when try to put a Player identified by -1")
	void givenAPlayerIdentifiedBy1_whenRunPutPlayer_thenGetANotFound() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString( new PlayerDTO()
																.name( "Hillmer Ch" )
																.uniformNumber( 5L )
																.phoneNumber( "1" )
																.position( "OUTFIELD" )
																.team( "MY TEAM" ) );


		MvcResult mvcResult =
				mockMvc.perform( MockMvcRequestBuilders.put( "/players/-1" )
										 .content( jsonContent )
										 .contentType( MediaType.APPLICATION_JSON )
										 .with( user( TEST_USER_ID ) )//Pretend we have a valid token
										 .with( csrf() )//Pretend we have a valid token

				).andExpect( status().isNotFound() )
						.andReturn();

		ErrorMessage errorMessage = objectMapper.readValue( mvcResult.getResponse().getContentAsString(), ErrorMessage.class );
		assertEquals( "Could not find player -1", errorMessage.getMessage() );

	}

	@Test
	@DisplayName("Deleting a Player identified by 2")
	void deletePlayer() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders.delete( "/players/2" )
								 .with( user( TEST_USER_ID ) )//Pretend we have a valid token
								 .with( csrf() )//Pretend we have a valid token

		).andExpect( status().isNoContent() );

	}

	@Test
	@DisplayName("Getting a NOT_FOUND when try to delete a Player identified by -1")
	void givenAPlayerIdentifiedBy1_whenRunDeletePlayer_thenGetANotFound() throws Exception {

		MvcResult mvcResult =
				mockMvc.perform( MockMvcRequestBuilders.delete( "/players/-1" )
										 .with( user( TEST_USER_ID ) )//Pretend we have a valid token
										 .with( csrf() )//Pretend we have a valid token

				).andExpect( status().isNotFound() )
						.andReturn();

		ErrorMessage errorMessage = objectMapper.readValue( mvcResult.getResponse().getContentAsString(), ErrorMessage.class );
		assertEquals( "Could not find player -1", errorMessage.getMessage() );

	}
}
