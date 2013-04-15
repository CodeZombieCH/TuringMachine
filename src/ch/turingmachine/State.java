package ch.turingmachine;

import java.util.*;

public class State {
	
	private ArrayList<Transition> state;
	private String name;
	
	public State() {
		state = new ArrayList<Transition>();
	}
	
	public State(String name) {
		state = new ArrayList<Transition>();
		this.setName(name);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
