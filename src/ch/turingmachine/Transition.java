package ch.turingmachine;

public class Transition
{
	private char[] input;
	private char[] output;
	private Direction[] direc;
	private State targetState;
	
	
	public Transition(int dimension) {
		input = new char[dimension];
		output = new char[dimension];
		direc = new Direction[dimension];
	}
	
	public Transition(String input, String output, String directions, State targetState) {
		if(targetState == null) {
			//throw new NullPointerException(); 
		}
		
		// We take the length of the first string as the dimension
		int dimension = input.length();
		
		this.input = input.toCharArray();
		this.output = output.toCharArray();
		
		this.direc = new Direction[dimension];
		for (int i = 0; i < directions.length(); i++) {
			char d = directions.charAt(i);
			
			try {
				this.direc[i] = Utilities.parseDirection(d);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.targetState = targetState;
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

	public State getTargetState() {
		return targetState;
	}

	public void setTargetState(State targetState) {
		this.targetState = targetState;
	}
}