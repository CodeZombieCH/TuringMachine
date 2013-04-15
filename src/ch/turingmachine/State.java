package ch.turingmachine;

import java.util.*;

public class State {
	
	private ArrayList<Transition> state;
	
	public State() {
		state = new ArrayList<Transition>();
	}
	
	public Transition getState(char[] input) {
		// check if possible
		
		// return transition
		
		throw new UnsupportedOperationException("Not implemented, go on Sämy");
	}
	
	public void addState(Transition transition) {
		state.add(transition);
	}
}
