package ch.turingmachine;

public class Run {

	public static void main(String[] args) {
		MachineFactory factory = new MachineFactory();
		
		/*
		// Validate arguments
		if(args.length!= 1) {
			System.exit(1);
		}
		*/
		
		TuringMachine machine;
		
		if(args.length == 1) {
			machine = factory.create(args[0]);
		}
		else
		{
			//machine = factory.createFactorialMachine();
			machine = factory.createBalooMachine();
			//machine = factory.createMultiplicationMachine();
		}
	
		machine.initialize();
		machine.prepare();
		machine.run();
		machine.terminate();

	}
}
