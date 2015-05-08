
public class GraphicMiniRobot implements Drawable{

	private MiniRobot miniRobot;
	
	public GraphicMiniRobot(MiniRobot miniRobot){
		this.miniRobot = miniRobot;
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
