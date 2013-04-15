package ch.turingmachine;

import java.util.*;

public class State {
	
	private ArrayList<Transition> state;
	
	public State() {
		state = new ArrayList<Transition>();
	}
	
	public void getState() {
		// check if possible
		
		// return transition
	}
	
	public void addState(Transition transition) {
		state.add(transition);
	}
}
