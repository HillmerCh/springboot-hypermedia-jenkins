package org.hillmerch.player.web.advice;

import javax.servlet.http.HttpServletRequest;

import org.hillmerch.player.client.handler.ErrorMessage;
import org.hillmerch.player.web.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class PlayerControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PlayerNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleCityNotFoundException(PlayerNotFoundException ex, HttpServletRequest httpServletRequest) {

		return ResponseEntity.status( HttpStatus.NOT_FOUND )
				.contentType( MediaType.APPLICATION_PROBLEM_JSON )
				.body( new ErrorMessage(httpServletRequest.getRequestURI(), ex.getMessage())); //como builder
	}

}
