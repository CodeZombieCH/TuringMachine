package ch.turingmachine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DisplayImage extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

    public DisplayImage(String state) {
       setImage(state);
    }
    
    public void setImage(String state) {
    	try {
			image = ImageIO.read(new File("C:/" + state + ".jpg"));
			this.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);        
    }

}