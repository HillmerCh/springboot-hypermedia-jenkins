package org.hillmerch.player.model.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hillmerch.player.model.dao.PlayerDao;
import org.hillmerch.player.model.model.Player;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

	private final PlayerDao playerDao;

	public LoadDatabase(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Preloading " + playerDao.save(new Player( "Babe Ruth", Player.Position.OUTFIELD, 3L, "555-01-123", "YANKEES")));
		logger.info("Preloading " + playerDao.save(new Player("Barry Bonds",  Player.Position.OUTFIELD, 25L, "777-01-123","GIANTS")));

		for(int it=25; it < 100; it++){

			logger.info("Preloading " + playerDao.save(new Player( "Babe Ruth (" + it + ")", Player.Position.OUTFIELD, (long) it, "555-01-123", "YANKEES")));


		}
	}
}
