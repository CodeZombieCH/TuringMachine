package ch.turingmachine;

public abstract class MachineBase {

	public MachineBase() {
		
	}
	
	public static void printTapes(Tape[] tapes) {
		for (int i = 0; i < tapes.length; i++) {
			System.out.println(tapes[i].toString());
		}
		System.out.println("**********");
	}
}
