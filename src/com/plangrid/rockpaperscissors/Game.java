package com.plangrid.rockpaperscissors;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Player> players = new ArrayList<>(); // cardinality = 2
	private List<Player> ackResult = new ArrayList<>();
	
	public Game(Player player) {
		players.add(player);
	}
	
	public boolean isFull() {
		return players.size() == 2;
	}
	
	public void addPlayer(Player player) {
		if (players.size() > 1) {
			throw new IllegalStateException("Tried to add " + player + " to full game: " + players);
		}
		players.add(player);
	}
	
	public Result setChoice(Player player, Choice choice) {
		player.setChoice(choice);
		return pollResult(player);
	}
	
	public synchronized Result pollResult(Player player) {
		Player second = null;
		for (Player p : players) {
			if (!p.equals(player)) {
				second = p;
				break;
			}
		}
		
		if (second == null || !second.hasChosen()) {
			return Result.WAIT;
		} else {
			Result result = player.getChoice().compare(second.getChoice());
			ackResult.add(player);
			if (ackResult.size() == players.size()) {
				// Reset choices for next round
				player.setChoice(null);
				second.setChoice(null);
				ackResult.removeAll(players);
			}
			return result;
		}
	}
}
