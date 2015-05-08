import java.awt.image.BufferedImage;


public class GraphicMiniRobot implements Drawable{

	private MiniRobot miniRobot;
	private BufferedImage image;
	
	
	public GraphicMiniRobot(MiniRobot miniRobot){
		this.miniRobot = miniRobot;
		image = Resources.getMiniRobot();
		
	}
	
	public void draw() {
		if(miniRobot.isAlive() && miniRobot != null){	// Ellenorzes, ki kell-e meg rajzolni
			//TODO
		}
		else{
			miniRobot=null;								//ref torlese, GC dolgozhat
			return;
		}
	}



}
