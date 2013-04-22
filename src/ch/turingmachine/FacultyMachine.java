package ch.turingmachine;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal.Color;

public class FacultyMachine extends MachineBase implements Machine {
	private State[] states;
	private State currentState;
	private Tape[] tapes;
	

	public FacultyMachine(State[] states, State initialState, Tape[] tapes) {
		this.states = states;
		this.currentState = initialState;
		this.tapes = tapes;
	}

	@Override
	public void run(int[] args) {
		// Validate arguments
		if(args.length != 1) {
			throw new IllegalArgumentException();
		}
		
		// Convert argument to input string for tape 1
		String input1 = new String(new char[args[0]]).replace("\0", "1");
		
		tapes[0].initialize(input1.toCharArray());
		
		// Initialize terminal
		Screen screen = TerminalFacade.createScreen();
		screen.startScreen();
		
		ScreenWriter writer = new ScreenWriter(screen);
		
		writer.drawString(0, 0, "╔═══════════════════════════════════════════════════════════════════════════════════════════╗", ScreenCharacterStyle.Bold);
		writer.drawString(0, 1, "║   Turing machine                                        by Marc-André, Marco und Samuel   ║", ScreenCharacterStyle.Bold);
		writer.drawString(0, 2, "╚═══════════════════════════════════════════════════════════════════════════════════════════╝", ScreenCharacterStyle.Bold);
		writer.drawString(0, 3, "    Control: [ENTER] = Run to the end                         Any other key for next step    ");
		
		screen.setCursorPosition(0, 9);
		
		int y = 5;

		printTapes(writer, tapes, this.currentState, y);
		screen.refresh();

		Key key = null;
		boolean runThrough = false;
		
		while (key == null || key.getKind() != Kind.Escape || runThrough) {
			this.stepCount++;

			char[] values = new char[3];

			// Get current values on all tapes
			for (int i = 0; i < tapes.length; i++) {
				values[i] = tapes[i].read();
			}

			Transition matchingTransaction = this.currentState.findTransition(values);

			if (matchingTransaction == null) {
				printTapes(writer, tapes, this.currentState, y);
				screen.refresh();
				
				Color color = writer.getForegroundColor();
				writer.setForegroundColor(Color.GREEN);
				writer.drawString(0, 9, "FINISHED!");
				writer.setForegroundColor(color);
				screen.refresh();
				
				break;
			}

			for (int i = 0; i < tapes.length; i++) {
				tapes[i].write(matchingTransaction.getOutput()[i],
						matchingTransaction.getDirection()[i]);
			}

			this.currentState = matchingTransaction.getTargetState();

			// Comment out for max performance :)
			printTapes(tapes, this.currentState);
			printTapes(writer, tapes, this.currentState, y);
			screen.refresh();
			
			do {
				key = screen.readInput();
			} while(!runThrough && key == null);
			
			if(key != null) {
				if (key.getKind() == Kind.Enter) {
					// Run through
					runThrough = true;
				}
				// Else: next step
			}
		}
		
		// Wait until user presses a key to terminate the application
		while(screen.readInput() == null) {
			
		}
		
		screen.stopScreen();
	}
}
