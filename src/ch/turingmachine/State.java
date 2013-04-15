package ch.turingmachine;

import java.util.*;

public class State {
	
	private ArrayList<Transition> state;
	
	public State() {
		state = new ArrayList<Transition>();
	}
	
	public Transition getState(char[] input) {
		// check if possible
		for (Transition t : state) {
			if(Arrays.equals(input, t.getInput())) {
				return t;
			}
		}
		// return null if no state is found
		return null;
	}
	
	public void addState(Transition transition) {
		state.add(transition);
	}
}
