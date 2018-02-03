package com.plangrid.rockpaperscissors;

import java.util.Objects;

public class Player {
	private String sessionId;
	private Choice currentChoice = null;
	
	public Player(String sessionId) {
		this.sessionId = sessionId;
	}

	public boolean hasChosen() {
		return this.currentChoice != null;
	}
	
	public void setChoice(Choice choice) {
		this.currentChoice = choice;
	}
	
	public Choice getChoice() {
		return currentChoice;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(sessionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(sessionId, other.sessionId);
	}

	@Override
	public String toString() {
		return "Player [sessionId=" + sessionId + ", currentChoice=" + currentChoice + "]";
	}
}
