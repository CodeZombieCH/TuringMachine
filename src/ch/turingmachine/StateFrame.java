package ch.turingmachine;

import javax.swing.JFrame;

public class StateFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private String machine;
	private DisplayImage panel;
	
	public StateFrame(String machine, String state) {
		this.machine = machine;
		panel = new DisplayImage(state);
		
		this.add(panel);
		this.setSize(640, 240);
	}
	
	public void setFrame(String state) {
		panel.setImage(state);
	}
}
