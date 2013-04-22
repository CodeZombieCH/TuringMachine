package ch.turingmachine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.turingmachine.Direction;
import ch.turingmachine.Tape;

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
	
	@Test
	public void testEmpty() {
		Tape tape = new Tape();
		assertEquals(Tape.BLANK, tape.read());
	}
	
	@Test
	public void testInitialize() {
		Tape tape = new Tape();
		tape.initialize("123456".toCharArray());
		
		for (int i = 1; i < 6; i++) {
			assertEquals(Character.forDigit(i, 10), tape.read());
			tape.move(Direction.RIGHT);
		}
	}
}
