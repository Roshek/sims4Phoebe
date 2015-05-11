import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class GraphicRobot implements Drawable{

	private Robot robot;
	private BufferedImage image;

	
	public GraphicRobot(Robot robot){
		this.robot=robot;
		image = Resources.getRobot(robot);	// Resources osztalybol a megfelelo kep kinyerese
	}
	
	public void draw(Graphics g) {
		if(robot.isAlive()){	// Ellenorzes, hogy ki kell e meg rajzolni
				//pozicio kinyerese
				int x = robot.getPosition().getX();
				int y = robot.getPosition().getY();
				// eltolni kell, mert a drawimage a bal sarokhoz igazitja a kepet, nekunk meg a kozeppont van meg
				BufferedImage tmp=image;
				if ((x-30)>0) x=x-30;
				else{ 
					
					tmp=tmp.getSubimage(30-x, 0, tmp.getWidth()-30+x, tmp.getHeight());
					x=0;
				}
				if ((y-30)>0) y=y-30;
				else{ 
										
					tmp=tmp.getSubimage(0, 30-y, tmp.getWidth(), tmp.getHeight()-30+y);
					y=0;
				}
				//kirajzolas
				image = Resources.getRobot(robot);
				g.drawImage(tmp, x, y,null);
		}
		else{
			//robot=null;	// Itt nem toroljuk a referenciat, tovabbfejleszthetosegi lehetosegek miatt.
			return;
		}
		
	}

}
