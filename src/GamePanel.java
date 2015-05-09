import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class GamePanel extends JPanel {
	
	View view;
	BufferedImage background;
	
	public GamePanel(View x) {
		view=x;
		background=Resources.getMap();
	}
	
    protected void paintComponent(Graphics g){
    	super.paintComponent(g);
    	
    	g.drawImage(background, 0, 0, null);
    	
		view.drawAll();
	}

}
