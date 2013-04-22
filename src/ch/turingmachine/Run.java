package ch.turingmachine;

import java.io.IOException;

public class Run {

	public static void main(String[] args) {
		MachineFactory factory = new MachineFactory();
		
		Machine machine = factory.createFacultyMachine();
		machine.run(new int[] {5});
		
		//new MultiplicationMachine().run(new int[] {3, 5});
	}
}
