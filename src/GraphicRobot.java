import java.awt.image.BufferedImage;


public class GraphicRobot implements Drawable{

	private Robot robot;
	private BufferedImage image;
	
	public GraphicRobot(Robot robot){
		this.robot=robot;
		image = Resources.getRobot();				// Resources osztalybol a megfelelo kep kinyerese
	}
	
	public void draw() {
		if(robot.isAlive()){		// Ellenorzes, hogy ki kell e meg rajzolni
			//TODO
		}
		else{
			//robot=null;								// Itt nem toroljuk a referenciat, tovabbfejleszthetosegi lehetosegek miatt.
			return;
		}
		
	}

}
