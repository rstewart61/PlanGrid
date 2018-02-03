package com.plangrid.rockpaperscissors;

import java.util.concurrent.ConcurrentHashMap;

public class RPSEngine {
	private static ConcurrentHashMap<String, Player> playerMap = new ConcurrentHashMap<>();
	private static ConcurrentHashMap<Player, Game> gameMap = new ConcurrentHashMap<>();
	
	public static Player getPlayer(String sessionId) {
		Player player = playerMap.computeIfAbsent(sessionId, x -> new Player(x));
		synchronized (RPSEngine.class) {
			Game game = gameMap.get(player);
			if (game == null) {
				// Add player to existing game if one can be found
				for (Game g : gameMap.values()) {
					if (!g.isFull()) {
						g.addPlayer(player);
						gameMap.put(player, g);
						return player;
					}
				}
				// Create new game
				gameMap.put(player, new Game(player));
			}
		}
		return player;
	}
	
	public static Result submit(Player player, Choice choice) {
		Game game = gameMap.get(player);
		if (game == null) {
			throw new RuntimeException("Session expired. Please restart game.");
		}
		Result result = game.setChoice(player, choice);
		return result;
	}
	
	public static Result pollResult(Player player) {
		Game game = gameMap.get(player);
		if (game == null) {
			throw new RuntimeException("Session expired. Please restart game.");
		}
		Result result = game.pollResult(player);
		return result;
	}
}
