package ch.turingmachine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.turingmachine.State;
import ch.turingmachine.Transition;

public class StateTest {

	@Test
	public void test(){
		int actual;
		
		State state = new State();
		Transition transition = new Transition(1);
		
		transition.setInput(0);
		transition.setOutput(1);
		transition.setDirec(Left);
		
		
		actual = 101;
		state.addState(1);
		actual = state.getState(1);
		
		assertEquals('0',actual);
	}

}
