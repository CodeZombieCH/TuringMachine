package ch.turingmachine;

public class Run {

	public static void main(String[] args) {
		MachineFactory factory = new MachineFactory();
		
		TuringMachine machine = factory.createFactorialMachine();
		machine.initialize();
		machine.prepare(new int[] { 9 });
		machine.run();
		machine.terminate();
		
		//new MultiplicationMachine().run(new int[] {3, 5});
	}
}
