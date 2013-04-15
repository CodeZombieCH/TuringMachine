package ch.turingmachine;

import ch.turingmachine.Tape.Direction;

public class Transition
{
	private char[] input;
	private char[] output;
	private Direction[] direc;
	
	
	public Transition(int dimension) {
		input = new char[dimension];
		output = new char[dimension];
		direc = new Direction[dimension];
		
	}

	public char[] getOutput()
	{
		return output;
	}

	public void setOutput(char[] output)
	{
		this.output = output;
	}

	public char[] getInput()
	{
		return input;
	}

	public void setInput(char[] input)
	{
		this.input = input;
	}

	public Direction[] getDirec()
	{
		return direc;
	}

	public void setDirec(Direction[] directions)
	{
		this.direc = directions;
		
	}


}