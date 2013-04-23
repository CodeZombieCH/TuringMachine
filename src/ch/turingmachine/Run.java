package ch.turingmachine;

public class Run {

	public static void main(String[] args) {
		MachineFactory factory = new MachineFactory();
		/*
		TuringMachine machine = factory.createFactorialMachine();
		machine.initialize();
		machine.prepare(new int[] { 2 });
		machine.run();
		machine.terminate();
		*/
		
		TuringMachine machine = factory.createBalooMachine();
		machine.initialize();
		machine.prepare(new int[] { 3 });
		machine.run();
		machine.terminate();
		
		/*
		TuringMachine machine = factory.createMultiplicationMachine();
		machine.initialize();
		machine.prepare(new int[] { 5, 3 });
		machine.run();
		machine.terminate();
		*/
	}
}
