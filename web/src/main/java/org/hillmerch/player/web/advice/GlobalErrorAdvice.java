package org.hillmerch.player.web.advice;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;
import org.hillmerch.player.client.handler.ErrorMessage;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalErrorAdvice {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handlerException(ConstraintViolationException e, HttpServletRequest httpServletRequest){

		log.info("{}", httpServletRequest.getRequestURI());

		StringBuilder builder = new StringBuilder();

		ErrorMessage errorMessage =  new ErrorMessage();
		errorMessage.setStatus( HttpStatus.BAD_REQUEST.toString());
		errorMessage.setResource(httpServletRequest.getRequestURI());

		e.getConstraintViolations()
				.forEach(error-> builder.append(
						String.format("Value %s %s", error.getInvalidValue(), error.getMessage())
				));
		errorMessage.setMessage(builder.toString());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handlerException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest){

		log.info("{}", httpServletRequest.getRequestURI());

		ErrorMessage errorMessage =  new ErrorMessage();
		errorMessage.setStatus(HttpStatus.BAD_REQUEST.toString());
		errorMessage.setResource(httpServletRequest.getRequestURI());

		errorMessage.setErrorMessageList(
				e.getBindingResult()
						.getAllErrors()
						.stream()
						.map(error-> new ErrorMessage( errorMessage.getResource(), "Invalid property " + ((FieldError) error).getField() + ": " + error.getDefaultMessage()))
						.collect( Collectors.toList()));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorMessage> handlerException(HttpMessageNotReadableException e, HttpServletRequest httpServletRequest){

		log.info("{}", httpServletRequest.getRequestURI());

		ErrorMessage errorMessage =  new ErrorMessage();
		errorMessage.setStatus(HttpStatus.BAD_REQUEST.toString());
		errorMessage.setResource(httpServletRequest.getRequestURI());
		errorMessage.setMessage("Invalid body " + e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<ErrorMessage> handlerException(PropertyReferenceException e, HttpServletRequest httpServletRequest){

		log.info("{}", httpServletRequest.getRequestURI());

		ErrorMessage errorMessage =  new ErrorMessage();
		errorMessage.setStatus(HttpStatus.BAD_REQUEST.toString());
		errorMessage.setResource(httpServletRequest.getRequestURI());
		errorMessage.setMessage("Invalid body " + e.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}



}
