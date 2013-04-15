package ch.turingmachine;

import java.util.Stack;

public class Tape {
	public static final char BLANK = 'B';
	
	private Stack<Character> leftTape = new Stack<Character>();
	private Stack<Character> rightTape = new Stack<Character>();
	private char current;
	private int blankOffset = 0;
	
	public Tape() {
		
	}
	
	public Tape(String input) {
		this(input.toCharArray());
	}
	
	public Tape(char[] input) {
		for(char c : input) {
			this.rightTape.push(c);
		}
		
		this.current = rightTape.pop();
	}
	
	public char read() {
		if(this.blankOffset == 0) {
			return this.current;
		}
		else {
			return BLANK;
		}
	}
	
	public void write(char value, Direction direction) {
		if(direction == Direction.LEFT) {
			if(this.blankOffset < 0) {
				this.blankOffset--;
			}
			else if(this.blankOffset > 0) {
				this.blankOffset--;
			}
			else
			{
				this.rightTape.push(this.current);
	
				if(this.leftTape.empty()) {
					this.blankOffset--;
					this.current = BLANK;
				}
				else {
					this.current = leftTape.pop();
				}
			}
		}
		else if(direction == Direction.RIGHT) {
			if(this.blankOffset < 0) {
				this.blankOffset++;
			}
			else if(this.blankOffset > 0) {
				this.blankOffset++;
			}
			else
			{
				leftTape.push(this.current);
	
				if(this.rightTape.empty()) {
					this.blankOffset++;
					this.current = BLANK;
				}
				else {
					this.current = rightTape.pop();
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return leftTape.toString() + current + rightTape.toString();
	}
	
	public enum Direction {
		LEFT,
		RIGHT,
		NEUTRAL
	}
}
