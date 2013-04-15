package ch.turingmachine;

import java.util.Stack;

public class Tape {
	public static final char BLANK = 'B';
	
	private Stack<Character> leftTape = new Stack<Character>();
	private Stack<Character> rightTape = new Stack<Character>();
	private char current;
	private int blankOffset = 0;
	
	public Tape() {
		this.current = BLANK;
	}
	
	public Tape(String input) {
		this(input.toCharArray());
	}
	
	public Tape(char[] input) {
		input = reverse(input);
		
		for(char c : input) {
			this.rightTape.push(c);
		}
		
		this.current = rightTape.pop();
	}
	
	public char read() {
		return this.current;
	}
	
	public void write(char value, Direction direction) {
		if(direction == Direction.LEFT) {
			if(this.blankOffset > 0) {
				this.blankOffset--;
				
				if(this.blankOffset == 0) {
					// Back in range
					this.current = leftTape.pop();
				}
			}
			else if(this.blankOffset < 0) {
				this.blankOffset--;
			}
			else
			{
				this.rightTape.push(value);
	
				if(this.leftTape.empty()) {
					// Leaving range
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
				
				if(this.blankOffset == 0) {
					// Back in range
					this.current = rightTape.pop();
				}
			}
			else if(this.blankOffset > 0) {
				this.blankOffset++;
			}
			else
			{
				this.leftTape.push(value);
	
				if(this.rightTape.empty()) {
					// Leaving range
					this.blankOffset++;
					this.current = BLANK;
				}
				else {
					this.current = rightTape.pop();
				}
			}
		}
	}
	
	private static char[] reverse(char[] input) {
		char[] output = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			output[input.length - i - 1] = input[i];
		}
		
		return output;
	}
	
	@Override
	public String toString() {
		Character[] chars = new Character[rightTape.size()];
		rightTape.copyInto(chars);
		String right = "";
		for (int i = chars.length - 1; i >= 0; i--) {
			right += chars[i] + "  ";
		}
		right = right.trim();
		
		chars = new Character[leftTape.size()];
		leftTape.copyInto(chars);
		String left = "";
		for (int i = 0; i < chars.length; i++) {
			left += chars[i] + "  ";
		}
		left = left.trim();
		
		return (left + " [" + current + "] " + right).trim();
	}
}
