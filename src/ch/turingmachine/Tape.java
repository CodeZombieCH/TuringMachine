package ch.turingmachine;

import java.util.Stack;

public class Tape {
	private Stack<Character> leftTape;
	private Stack<Character> rightTape;
	private char current;
	
	public Tape() {
		
	}
	
	public char read() {
		return this.current;
	}
	
	public void write(char value, Direction direction) {
		if(direction == Direction.Left) {
			rightTape.push(this.current);
			this.current = leftTape.pop();
		}
		else if(direction == Direction.Right) {
			leftTape.push(this.current);
			this.current = rightTape.pop();
		}
	}
	
	public enum Direction {
		Left,
		Right,
		Neutral
	}
}
