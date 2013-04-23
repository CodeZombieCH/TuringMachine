package ch.turingmachine;

import java.text.NumberFormat;
import java.util.Locale;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal.Color;

public class TuringMachine {
	// Terminal stuff
	private Screen screen;
	private final int offsetTop = 5;
	
	// What makes the Turing machine
	private State[] states;
	private State currentState;
	private Tape[] tapes;
	
	private int stepCount = 0;
	private long startTime = 0;
	private long endTime = 0;

	
	public TuringMachine(State[] states, State initialState, Tape[] tapes) {
		this.states = states;
		this.currentState = initialState;
		this.tapes = tapes;
	}

	public void initialize() {
		// Initialize terminal
		this.screen = TerminalFacade.createScreen();
		this.screen.startScreen();
		
		ScreenWriter writer = new ScreenWriter(screen);

		writer.drawString(0, 0, "╔═══════════════════════════════════════════════════════════════════════════════════════════╗");
		writer.drawString(0, 1, "║   Turing machine                                        by Marc-André, Marco und Samuel   ║");
		writer.drawString(0, 2, "╚═══════════════════════════════════════════════════════════════════════════════════════════╝");
		writer.drawString(0, 3, "    Control: [ENTER] = Run to the end                         Any other key for next step    ");
	}

	public void prepare(int[] args) {
		
		/*
		ScreenWriter writer = new ScreenWriter(screen);
		
		writer.drawString(0, offsetTop, "Please enter initial value for tape (defaut is full of BLANKS):");
		
		for (int i = 0; i < tapes.length; i++) {
			
		}
		*/
		
		// Validate arguments
		if (args.length != 1) {
			throw new IllegalArgumentException();
		}

		// Convert argument to input string for tape 1
		String input1;
		if (args[0] != 0)
			input1 = new String(new char[args[0]]).replace("\0", "1");
		else
			input1 = "B";

		tapes[0].initialize(input1.toCharArray());
	}

	
	public void run() {
		// Expecting tapes to be initialized
		
		ScreenWriter writer = new ScreenWriter(screen);

		int y = 5;

		printTapes(writer, tapes, this.currentState, y);
		screen.refresh();
		
		Key key = null;
		boolean runThrough = false;
		
		// Wait for user to start
		do {
			key = screen.readInput();
		} while (key == null);
		
		if(key.getKind() == Kind.Escape) {
			return;
		}
		else if(key.getKind() == Kind.Enter) {
			runThrough = true;
		}
		
		// Let's go
		this.startTime = System.nanoTime();
		
		while (key == null || key.getKind() != Kind.Escape || runThrough) {
			this.stepCount++;

			char[] values = new char[3];

			// Get current values on all tapes
			for (int i = 0; i < tapes.length; i++) {
				values[i] = tapes[i].read();
			}

			Transition matchingTransition = this.currentState
					.findTransition(values);

			if (matchingTransition == null) {
				// No further transition found, were done!
				
				// Calculate execution time (including breaks between steps in manual mode)
				this.endTime = System.nanoTime();
				NumberFormat formatter = NumberFormat.getInstance(new Locale("en"));
				String duration = formatter.format(endTime - startTime);
				
				
				printTapes(writer, tapes, this.currentState, y);
				screen.refresh();

				Color color = writer.getForegroundColor();
				writer.setForegroundColor(Color.GREEN);
				writer.drawString(0, 9, "FINISHED in " + duration + " nano seconds");
				writer.setForegroundColor(color);
				
				printTapesAsUnaryNumber(writer, 10);
				
				screen.refresh();
				
				// Wait until user presses a key to terminate the application
				while (screen.readInput() == null) {

				}

				return;
			}

			for (int i = 0; i < tapes.length; i++) {
				tapes[i].write(matchingTransition.getOutput()[i],
						matchingTransition.getDirection()[i]);
			}

			this.currentState = matchingTransition.getTargetState();

			// Comment out for max performance :)
			//printTapes(tapes, this.currentState);
			printTapes(writer, tapes, this.currentState, y);
			screen.refresh();

			do {
				key = screen.readInput();
			} while (!runThrough && key == null);

			if (key != null) {
				if (key.getKind() == Kind.Enter) {
					// Run through
					runThrough = true;
				}
				// Else: next step
			}
		}
	}

	public void terminate() {
		this.screen.stopScreen();
	}
	
	public void printTapes(Tape[] tapes, State state) {
		System.out.printf("********** Step %05d: State \"%s\" **********\n", this.stepCount, state.getName());
		for (int i = 0; i < tapes.length; i++) {
			System.out.printf("#%d: %s\n", i+1, tapes[i].toString());
		}
	}
	
	public void printTapes(ScreenWriter writer, Tape[] tapes, State state, int y) {
		writer.drawString(0, y, "Step count: " + this.stepCount);
		writer.drawString(93 - 7 - state.getName().length(), y, "State: " + state.getName());
		
		for (int i = 0; i < tapes.length; i++) {
			tapes[i].printRange(writer, y + i + 1, 15);
		}
	}
	
	public void printTapesAsUnaryNumber(ScreenWriter writer, int y) {
		for (int i = 0; i < tapes.length; i++) {
			try {
				writer.drawString(0, y + i, "Tape #" + (i + 1) + " as unary number: " + tapes[i].readAsUnary('1'));
			} catch (Exception ex) {
				writer.drawString(0, y + i, "Tape #" + (i + 1) + " as unary number: " + ex.getMessage());
			}
		}
	}
}
