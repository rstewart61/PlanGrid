package com.plangrid.rockpaperscissors;

public class Test {
	public static void main(String [] args) {
		String p1Id = "one", p2Id = "two";
		for (Choice p1c : Choice.values()) {
			for (Choice p2c : Choice.values()) {
				Player p1 = RPSEngine.getPlayer(p1Id);
				RPSEngine.submit(p1, p1c);
				Result waitResult = RPSEngine.pollResult(p1);
				if (waitResult != Result.WAIT) {
					throw new IllegalStateException("Expected WAIT after only first player's move");
				}
				if (!p1.hasChosen()) {
					throw new IllegalStateException("Expected hasChosen() == true");
				}
				Player p2 = RPSEngine.getPlayer(p2Id);
				Result p2ExpectedResult = p2c.compare(p1c);
				Result result = RPSEngine.submit(p2, p2c);
				if (result != p2ExpectedResult) {
					throw new IllegalStateException("For p2 choices " + p2c + " " + p1c + ", expected result = " + p2ExpectedResult + ", actual = " + result);
				}
				Result p1ExpectedResult = p1c.compare(p2c);
				Result ackResult = RPSEngine.pollResult(p1);
				if (ackResult != p1ExpectedResult) {
					throw new IllegalStateException("For p1 choices " + p1c + " " + p2c + ", expected result = " + p1ExpectedResult + ", actual = " + ackResult);
				}
			}
		}
		System.out.println("All tests successfully passed");
	}
}
