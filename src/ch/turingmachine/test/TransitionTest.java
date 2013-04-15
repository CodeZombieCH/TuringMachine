package ch.turingmachine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.turingmachine.Transition;
import ch.turingmachine.Tape.Direction;

public class TransitionTest {

	@Test
	public void testDirectAssign() {
		Transition t = new Transition(3);
		Direction[] actdir = new Direction[] {Direction.Neutral, Direction.Right, Direction.Right};
		t.setInput("11B".toCharArray());
		t.setOutput("111".toCharArray());
		t.setDirec(actdir);
		assertArrayEquals("11B".toCharArray(), t.getInput());
		assertArrayEquals("111".toCharArray(), t.getOutput());
		assertArrayEquals(actdir, t.getDirec());
	}
	
	@Test
	public void testParseAssign() {
		Transition t = new Transition("101", "110", "LRN");
		assertArrayEquals("101".toCharArray(), t.getInput());
		assertArrayEquals("110".toCharArray(), t.getOutput());
		assertArrayEquals(new Direction[] {Direction.Left, Direction.Right, Direction.Neutral}, t.getDirec());
	}

}
