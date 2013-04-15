package ch.turingmachine;

import java.io.IOException;

public class Run {

	public static void main(String[] args) {

		//Multiplication		
		State[] states = new State[] {
			new State("State 1"),
			new State("State 2")
		};
		State current = states[0];

		states[0].addState(new Transition("11B", "111", "NRR", states[0]));
		states[0].addState(new Transition("1BB", "BBB", "RLN", states[1]));
		states[1].addState(new Transition("11B", "111", "NLR", states[1]));
		states[1].addState(new Transition("1BB", "BBB", "RRN", states[0]));

		Tape[] tapes = new Tape[] {
			new Tape("111"),
			new Tape("111"),
			new Tape("BBBBBBBBBBBB")
		};

		printTapes(tapes);

		while(true) {

			char[] values = new char[3];

			// Get current values on all tapes
			for (int i = 0; i < tapes.length; i++) {
				values[i] = tapes[i].read();
			}

			Transition matchingTransaction = current.findTransaction(values);

			if(matchingTransaction == null) {
				System.out.println("Finished!");
				return;
			}

			for(int i = 0; i < tapes.length; i++) {
				tapes[i].write(matchingTransaction.getOutput()[i], matchingTransaction.getDirection()[i]);
			}

			current = matchingTransaction.getTargetState();

			printTapes(tapes);

			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void printTapes(Tape[] tapes) {
		for (int i = 0; i < tapes.length; i++) {
			System.out.println(tapes[i].toString());
		}
		System.out.println("**********");
	}
}
