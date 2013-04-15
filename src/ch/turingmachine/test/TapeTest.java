package ch.turingmachine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.turingmachine.Tape;
import ch.turingmachine.Tape.Direction;

public class TapeTest {

	@Test
	public void testInitialization() {
		Tape tape = new Tape("0000100");
		
		char actual;
		actual = tape.read();
		assertEquals('0', actual);
		
		tape.write('0', Direction.RIGHT);
		tape.write('0', Direction.RIGHT);
		tape.write('0', Direction.RIGHT);
		tape.write('0', Direction.RIGHT);
		tape.write('1', Direction.RIGHT);
		tape.write('0', Direction.RIGHT);
		
		actual = tape.read();
		assertEquals('0', actual);
	}

	@Test
	public void testOutOfBounds() {
		Tape tape = new Tape("010");
		
		// To the left
		tape.write('0', Direction.LEFT);
		tape.write(Tape.BLANK, Direction.LEFT);
		assertEquals(Tape.BLANK, tape.read());
		
		// Back to start
		tape.write(Tape.BLANK, Direction.RIGHT);
		tape.write(Tape.BLANK, Direction.RIGHT);
		assertEquals('0', tape.read());
		
		// At the right end
		tape.write('0', Direction.RIGHT);
		tape.write('1', Direction.RIGHT);
		assertEquals('0', tape.read());
		
		// Out on the right
		tape.write('0', Direction.RIGHT);
		tape.write(Tape.BLANK, Direction.RIGHT);
		assertEquals(Tape.BLANK, tape.read());
		
		// Back to end
		tape.write(Tape.BLANK, Direction.LEFT);
		tape.write(Tape.BLANK, Direction.LEFT);
		assertEquals('0', tape.read());
	}
}
