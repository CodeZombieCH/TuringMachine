package ch.turingmachine;

public class MachineFactory {

	public MachineFactory() {
		// TODO Auto-generated constructor stub
	}

	
	public TuringMachine createMultiplicationMachine() {
		return null;
	}
	
	public TuringMachine createFactorialMachine() {
		State[] states = new State[] {
				new State("State 1"),
				new State("State 2"),
				new State("State 3"),
				new State("State 4"),
				new State("State 5"),
				new State("State 6"),
				new State("State 7")
		};
		
		states[0].addTransition(new Transition("BBB", "BB1", "NNN", states[0]));
		states[0].addTransition(new Transition("1BB", "11B", "RRN", states[1]));
		states[1].addTransition(new Transition("1BB", "11B", "RRN", states[1]));
		states[1].addTransition(new Transition("BBB", "BBB", "NLN", states[1]));
		states[1].addTransition(new Transition("B1B", "BBB", "LLN", states[2]));
		states[2].addTransition(new Transition("1BB", "BB1", "NNN", states[2]));
		states[2].addTransition(new Transition("11B", "11B", "NNN", states[3]));
		states[3].addTransition(new Transition("11B", "111", "NLR", states[3]));
		states[3].addTransition(new Transition("1BB", "BBB", "LRN", states[4]));
		states[3].addTransition(new Transition("B1B", "BBB", "NLL", states[5]));
		states[4].addTransition(new Transition("11B", "111", "NRR", states[4]));
		states[4].addTransition(new Transition("1BB", "BBB", "LLN", states[3]));
		states[4].addTransition(new Transition("B1B", "BBB", "NRL", states[6]));
		states[5].addTransition(new Transition("B11", "11B", "RNL", states[5]));
		states[5].addTransition(new Transition("B1B", "B1B", "LNN", states[3]));
		states[6].addTransition(new Transition("B11", "11B", "RNL", states[6]));
		states[6].addTransition(new Transition("B1B", "B1B", "LNN", states[4]));
		
		Tape[] tapes = new Tape[] {
				new Tape("B"),
				new Tape("B"),
				new Tape("B")
		};
		
		return new TuringMachine(states, states[0], tapes);
	}
}
