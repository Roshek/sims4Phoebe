
public class GraphicRobot implements Drawable{

	private Robot robot;
	
	public GraphicRobot(Robot robot){
		this.robot=robot;
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
