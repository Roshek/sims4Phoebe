import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class GraphicRobot implements Drawable{

	private Robot robot;
	private BufferedImage image;
	private Graphics g;

	
	public GraphicRobot(Robot robot){
		this.robot=robot;
		image = Resources.getRobot();	// Resources osztalybol a megfelelo kep kinyerese
	}
	
	public void draw() {
		if(robot.isAlive()){	// Ellenorzes, hogy ki kell e meg rajzolni
				//pozicio kinyerese
				int x = robot.getPosition().getX();
				int y = robot.getPosition().getY();
				// eltolni kell, mert a drawimage a bal sarokhoz igazitja a kepet, nekunk meg a kozeppont van meg
				if ((x-30)>0) x=x-30; 
				if ((y-30)>0) y=y-30;
				//kirajzolas
				g.drawImage(image, x, y,null);
		}
		else{
			//robot=null;	// Itt nem toroljuk a referenciat, tovabbfejleszthetosegi lehetosegek miatt.
			return;
		}
		
	}

}
