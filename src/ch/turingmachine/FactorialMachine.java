package ch.turingmachine;

public class FactorialMachine extends MachineBase implements Machine {

	public FactorialMachine() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(int[] args) {
		// Validate arguments
		if(args.length != 1) {
			throw new IllegalArgumentException();
		}
		
		// Convert argument to input string for tape 1
		String input1;
		if(args[0]!=0)
			input1 = new String(new char[args[0]]).replace("\0", "1");
		else
			input1 = "B";
		
		
		State[] states = new State[] { new State("State 1"),
				new State("State 2"), new State("State 3"),
				new State("State 4"), new State("State 5"),
				new State("State 6"), new State("State 7") };
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
		
		Tape[] tapes = new Tape[] { new Tape(input1), new Tape("B"),
				new Tape("B") };

		printTapes(tapes, current);

		while (true) {
			this.stepCount++;

			char[] values = new char[3];

			// Get current values on all tapes
			for (int i = 0; i < tapes.length; i++) {
				values[i] = tapes[i].read();
			}

			Transition matchingTransaction = current.findTransaction(values);

			if (matchingTransaction == null) {
				printTapes(tapes, current);
				System.out.println("Finished!");
				return;
			}

			for (int i = 0; i < tapes.length; i++) {
				tapes[i].write(matchingTransaction.getOutput()[i],
						matchingTransaction.getDirection()[i]);
			}

			current = matchingTransaction.getTargetState();

			// Comment out for max performance :)
			printTapes(tapes, current);
		}
	}
}
