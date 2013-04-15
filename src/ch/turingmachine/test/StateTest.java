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
		Transition transition = new Transition(input, output, direc, new State("s1"));
		
		state.addState(transition);
		actual = state.findTransaction(input.toCharArray());
		
		assertArrayEquals("110".toCharArray() , actual.getOutput());
	}
}
