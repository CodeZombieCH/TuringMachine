package ch.turingmachine;

public abstract class MachineBase {
	protected int stepCount = 0;
	
	public MachineBase() {
		
	}
	
	public void printTapes(Tape[] tapes, State state) {
		System.out.printf("********** Step %05d: State \"%s\" **********\n", this.stepCount, state.getName());
		for (int i = 0; i < tapes.length; i++) {
			System.out.printf("#%d: %s\n", i+1, tapes[i].toString());
		}
	}
}
