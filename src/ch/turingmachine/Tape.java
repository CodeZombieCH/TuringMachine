package ch.turingmachine;

import java.util.Stack;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal.Color;

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
	
	public void initialize(char[] input) {
		this.current = input[0];
		
		for (int i = input.length - 1; i > 0; i--) {
			this.rightTape.push(input[i]);
		}
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
				this.rightTape.push(value);
			}
			else
			{
				this.rightTape.push(value);
	
				if(this.leftTape.empty()) {
					// Leaving range
					//this.blankOffset--;
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
				this.leftTape.push(value);
			}
			else
			{
				this.leftTape.push(value);
	
				if(this.rightTape.empty()) {
					// Leaving range
					//this.blankOffset++;
					this.current = BLANK;
				}
				else {
					this.current = rightTape.pop();
				}
			}
		}
		// Neutral
		else
		{
			this.current = value;
		}
	}
	
	/**
	 * Shorthand for moving only the head
	 * @param direction
	 */
	public void move(Direction direction) {
		this.write(this.read(), direction);
	}
	
	private static char[] reverse(char[] input) {
		char[] output = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			output[input.length - i - 1] = input[i];
		}
		
		return output;
	}
	
	@Override
	public String toString() {		Character[] chars = new Character[rightTape.size()];		rightTape.copyInto(chars);		String right = "";
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
	
	public void printRange(ScreenWriter writer, int y, int range) {
		// Expect cursor to be on the right line
		
		// Left
		for (int i = 0; i < range; i++) {
			int position = this.leftTape.size() - i - 1;
			
			if(position >= 0) {
				writer.drawString((range - i - 1) * 3, y, " " + this.leftTape.get(position) + " ");
			}
			else {
				writer.drawString((range - i - 1) * 3, y, " " + BLANK + " ");
			}
		}
		
		// Current
		Color color = writer.getForegroundColor();
		
		writer.setForegroundColor(Color.YELLOW);
		writer.drawString(range * 3, y, "[" + this.current + "]", ScreenCharacterStyle.Bold);
		writer.setForegroundColor(color);

		// Right
		for (int i = 0; i < range; i++) {
			int position = this.rightTape.size() - i - 1;
			
			if(position >= 0) {
				writer.drawString((range + i + 1) * 3, y, " " + this.rightTape.get(position) + " ");
			}
			else {
				writer.drawString((range + i + 1) * 3, y, " " + BLANK + " ");
			}
		}
	}
	
	public void printCurrent(ScreenWriter writer, int y, int range) {
		// Current
		Color color = writer.getForegroundColor();
		
		writer.setForegroundColor(Color.YELLOW);
		writer.drawString(range * 3, y, "[" + this.current + "]");
		writer.setForegroundColor(color);
	}
}
