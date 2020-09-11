package org.hillmerch.player.web.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class SecurityErrorAdvice {
	//For future security errors handling
}
