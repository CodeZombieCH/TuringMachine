package ch.turingmachine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.turingmachine.Direction;
import ch.turingmachine.State;
import ch.turingmachine.Transition;

public class TransitionTest {

	@Test
	public void testDirectAssign() {
		Transition t = new Transition(3);
		Direction[] actdir = new Direction[] {Direction.NEUTRAL, Direction.RIGHT, Direction.RIGHT};
		t.setInput("11B".toCharArray());
		t.setOutput("111".toCharArray());
		t.setDirec(actdir);
		assertArrayEquals("11B".toCharArray(), t.getInput());
		assertArrayEquals("111".toCharArray(), t.getOutput());
		assertArrayEquals(actdir, t.getDirec());
	}
	
	@Test
	public void testParseAssign() {
		State s = new State();
		Transition t = new Transition("101", "110", "LRN", s);
		assertArrayEquals("101".toCharArray(), t.getInput());
		assertArrayEquals("110".toCharArray(), t.getOutput());
		assertArrayEquals(new Direction[] {Direction.LEFT, Direction.RIGHT, Direction.NEUTRAL}, t.getDirec());
	}

}
