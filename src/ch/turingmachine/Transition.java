package ch.turingmachine;

public class Transition
{
	private char[] input;
	private char[] output;
	private char[] direction;
	
	public Transition(int inp, int outp, int dire) {
		setInput(new char[inp]);
		setOutput(new char[outp]);
		setDirection(new char[dire]);
		
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

	public char[] getDirection()
	{
		return direction;
	}

	public void setDirection(char[] direction)
	{
		this.direction = direction;
	}


	
}