import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JPanel;


public class GamePanel extends JPanel {
	
	View view;
	
	public GamePanel(View x) {
		view=x;
	}
	
    protected void paintComponent(Graphics g){
		view.drawAll();
	}

}
