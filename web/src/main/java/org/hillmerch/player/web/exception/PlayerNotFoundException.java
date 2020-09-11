package org.hillmerch.player.web.exception;

public class PlayerNotFoundException extends RuntimeException{
	public PlayerNotFoundException(Long id) {
		super("Could not find player " + id);
	}
}
