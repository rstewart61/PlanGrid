package com.plangrid.rockpaperscissors;

public enum Choice {
	ROCK,
	PAPER,
	SCISSORS;
	
	public Result compare(Choice other) {
		switch (this) {
		case PAPER:
			switch (other) {
			case PAPER:    return Result.DRAW;
			case ROCK:     return Result.WIN;
			case SCISSORS: return Result.LOSE;
			}
			break;
		case ROCK:
			switch (other) {
			case PAPER:    return Result.LOSE;
			case ROCK:     return Result.DRAW;
			case SCISSORS: return Result.WIN;
			}
			break;
		case SCISSORS:
			switch (other) {
			case PAPER:    return Result.WIN;
			case ROCK:     return Result.LOSE;
			case SCISSORS: return Result.DRAW;
			}
		}
		throw new IllegalStateException("Unexpected move comparison " + this + " vs " + other);
	}
}
