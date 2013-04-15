package ch.turingmachine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.turingmachine.State;
import ch.turingmachine.Transition;

public class StateTest {

	@Test
	public void test(){
		
		String input = "101";
		String output = "110";
		String direc = "LRN";
		Transition actual;
		
		State state = new State();
		Transition transition = new Transition(input, output, direc, new State());
		
		state.addState(transition);
		actual = state.getState("101110LRN".toCharArray());
		
		
		assertEquals("101110LRN",actual);
	}

}
