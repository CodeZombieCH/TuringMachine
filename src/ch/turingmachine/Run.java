package ch.turingmachine;

public class Run {
	
	public static void main(String[] args) {
		
		//Multiplication
		State s1 = new State();
		State s2 = new State();
		
		Transition t1 = new Transition("11B", "111", "NRR", s1);
		Transition t2 = new Transition("1BB", "BBB", "RLN", s2);
		Transition t3 = new Transition("11B", "111", "NLR", s2);
		Transition t4 = new Transition("1BB", "BBB", "RRN", s1);
	}
}
