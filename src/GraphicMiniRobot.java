import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class GraphicMiniRobot implements Drawable{

	private MiniRobot miniRobot;
	private BufferedImage image;
	
	
	public GraphicMiniRobot(MiniRobot miniRobot){
		this.miniRobot = miniRobot;
		image = Resources.getMiniRobot(); // Resources osztalybol a megfelelo kep kinyerese
		
	}
	
	public void draw(Graphics g) {
		if(miniRobot != null && miniRobot.isAlive()){	// Ellenorzes, ki kell-e meg rajzolni
			//pozicio kinyerese
			int x = miniRobot.getPosition().getX();
			int y = miniRobot.getPosition().getY();
			// eltolni kell, mert a drawimage a bal sarokhoz igazitja a kepet, nekunk meg a kozeppont van meg
			/*if ((x-15)>0) x=x-15; 
			if ((y-15)>0) y=y-15;*/
			BufferedImage tmp=image;
			if ((x-15)>0) x=x-15;
			else{ 				
				
				tmp=tmp.getSubimage(15-x, 0, tmp.getWidth()-15+x, tmp.getHeight());				//Kep levagasa a palya szelen
				x=0;
			}
			if ((y-15)>0) y=y-15;
			else{ 
									
				tmp=tmp.getSubimage(0, 15-y, tmp.getWidth(), tmp.getHeight()-15+y);
				y=0;
			}
			//kirajzolas
			g.drawImage(image, x, y,null);
		}
		else{
			miniRobot=null;	//ref torlese, GC dolgozhat
			return;
		}
	}



}
