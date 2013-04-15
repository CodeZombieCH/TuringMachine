package ch.turingmachine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.turingmachine.Tape;
import ch.turingmachine.Tape.Direction;

public class TapeTest {

	@Test
	public void test() {
		Tape tape = new Tape("0000100");
		
		char actual;
		actual = tape.read();
		assertEquals('0', actual);
		
		tape.write('0', Direction.Right);
		tape.write('0', Direction.Right);
		tape.write('0', Direction.Right);
		tape.write('0', Direction.Right);
		tape.write('1', Direction.Right);
		tape.write('0', Direction.Right);
		
		actual = tape.read();
		assertEquals('0', actual);
	}

}
