package ch.turingmachine;

public class MultiplicationMachine extends MachineBase implements Machine {

	public MultiplicationMachine() {

	}

	@Override
	public void run(int[] args) {
		// Validate arguments
		if(args.length != 2) {
			throw new IllegalArgumentException();
		}
		
		// Convert arguments to input strings for tapes
		String input1 = new String(new char[args[0]]).replace("\0", "1");
		String input2 = new String(new char[args[1]]).replace("\0", "1");
		
		State[] states = new State[] {
				new State("State 1"),
				new State("State 2") };
		State current = states[0];

		states[0].addState(new Transition("11B", "111", "NRR", states[0]));
		states[0].addState(new Transition("1BB", "BBB", "RLN", states[1]));
		states[1].addState(new Transition("11B", "111", "NLR", states[1]));
		states[1].addState(new Transition("1BB", "BBB", "RRN", states[0]));

		Tape[] tapes = new Tape[] {
				new Tape(input1),
				new Tape(input2),
				new Tape("B") };

		printTapes(tapes);

		while (true) {

			char[] values = new char[3];

			// Get current values on all tapes
			for (int i = 0; i < tapes.length; i++) {
				values[i] = tapes[i].read();
			}

			Transition matchingTransaction = current.findTransaction(values);

			if (matchingTransaction == null) {
				System.out.println("Finished!");
				return;
			}

			for (int i = 0; i < tapes.length; i++) {
				tapes[i].write(matchingTransaction.getOutput()[i],
						matchingTransaction.getDirection()[i]);
			}

			current = matchingTransaction.getTargetState();

			printTapes(tapes);

			/*
			try {
				System.in.read();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
	}
}
