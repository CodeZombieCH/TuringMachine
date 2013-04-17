package ch.turingmachine;

import java.io.IOException;

public class Run {

	public static void main(String[] args) {

		//Multiplication		
//		State[] states = new State[] {
//			new State("State 1"),
//			new State("State 2")
//		};
//		State current = states[0];
//
//		states[0].addState(new Transition("11B", "111", "NRR", states[0]));
//		states[0].addState(new Transition("1BB", "BBB", "RLN", states[1]));
//		states[1].addState(new Transition("11B", "111", "NLR", states[1]));
//		states[1].addState(new Transition("1BB", "BBB", "RRN", states[0]));
//
//		Tape[] tapes = new Tape[] {
//			new Tape("111"),
//			new Tape("111"),
//			new Tape("B")
//		};
//
//		printTapes(tapes);
//
//		while(true) {
//
//			char[] values = new char[3];
//
//			// Get current values on all tapes
//			for (int i = 0; i < tapes.length; i++) {
//				values[i] = tapes[i].read();
//			}
//
//			Transition matchingTransaction = current.findTransaction(values);
//
//			if(matchingTransaction == null) {
//				System.out.println("Finished!");
//				return;
//			}
//
//			for(int i = 0; i < tapes.length; i++) {
//				tapes[i].write(matchingTransaction.getOutput()[i], matchingTransaction.getDirection()[i]);
//			}
//
//			current = matchingTransaction.getTargetState();
//
//			printTapes(tapes);
//
//			try {
//				System.in.read();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		
		
		//Fakultät		
		State[] states = new State[] {
			new State("State 1"),
			new State("State 2"),
			new State("State 3"),
			new State("State 4"),
			new State("State 5"),
			new State("State 6"),
			new State("State 7")
		};
		State current = states[0];

		states[0].addState(new Transition("BBB", "BB1", "NNN", states[0]));
		states[0].addState(new Transition("1BB", "11B", "RRN", states[1]));
		states[1].addState(new Transition("1BB", "11B", "RRN", states[1]));
		states[1].addState(new Transition("BBB", "BBB", "NLN", states[1]));
		states[1].addState(new Transition("B1B", "BBB", "LLN", states[2]));
		states[2].addState(new Transition("1BB", "BB1", "NNN", states[2]));
		states[2].addState(new Transition("11B", "11B", "NNN", states[3]));
		states[3].addState(new Transition("11B", "111", "NLR", states[3]));
		states[3].addState(new Transition("1BB", "BBB", "LRN", states[4]));
		states[3].addState(new Transition("B1B", "BBB", "NLL", states[5]));
		states[4].addState(new Transition("11B", "111", "NRR", states[4]));
		states[4].addState(new Transition("1BB", "BBB", "LLN", states[3]));
		states[4].addState(new Transition("B1B", "BBB", "NRL", states[6]));
		states[5].addState(new Transition("B11", "11B", "RNL", states[5]));
		states[5].addState(new Transition("B1B", "B1B", "LNN", states[3]));
		states[6].addState(new Transition("B11", "11B", "RNL", states[6]));
		states[6].addState(new Transition("B1B", "B1B", "LNN", states[4]));
		

		Tape[] tapes = new Tape[] {
			new Tape("1111"),
			new Tape("B"),
			new Tape("B")
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
			
			System.out.println(current.getName());
			printTapes(tapes);

//			try {
//				System.in.read();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	private static void printTapes(Tape[] tapes) {
		for (int i = 0; i < tapes.length; i++) {
			System.out.println(tapes[i].toString());
		}
		System.out.println("**********");
	}
}
