package ch.turingmachine;

import java.io.IOException;

public class Run {

	public static void main(String[] args) {
		new FactorialMachine().run(new int[] {3});
		new MultiplicationMachine().run(new int[] {3, 5});
	}
}
