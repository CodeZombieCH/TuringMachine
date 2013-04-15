package ch.turingmachine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.turingmachine.Transition;
import ch.turingmachine.Tape.Direction;

public class TransitionTest {

	@Test
	public void test() {
		Transition t = new Transition(3);
		Direction[] actdir = new Direction[] {Direction.NEUTRAL, Direction.RIGHT, Direction.RIGHT};
		t.setInput("11B".toCharArray());
		t.setOutput("111".toCharArray());
		t.setDirec(actdir);
		assertArrayEquals("11B".toCharArray(), t.getInput());
		assertArrayEquals("111".toCharArray(), t.getOutput());
		assertArrayEquals(actdir, t.getDirec());
	}

}
