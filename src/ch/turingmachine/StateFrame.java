package ch.turingmachine;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class StateFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private String machine;
	private DisplayImage panel;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public StateFrame(String machine, String state) {
		this.machine = machine;
		panel = new DisplayImage(state);
		this.add(panel);
		this.setSize(640, 240);
		this.setLocation(dim.width/2 - getWidth()/2, (int) (dim.height*0.8 - getHeight()/2));
	}
	
	public void setFrame(String state) {
		panel.setImage(state);
	}
}
