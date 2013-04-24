package ch.turingmachine;

public class Run {

	public static void main(String[] args) {
		MachineFactory factory = new MachineFactory();
		
		/*
		TuringMachine machine = factory.createFactorialMachine();
		machine.initialize();
		machine.prepare();
		machine.run();
		machine.terminate();
		*/
		
		TuringMachine machine = factory.createBalooMachine();
		machine.initialize();
		machine.prepare();
		machine.run();
		machine.terminate();
		
		/*
		TuringMachine machine = factory.createMultiplicationMachine();
		machine.initialize();
		machine.prepare();
		machine.run();
		machine.terminate();
		*/
	}
}
