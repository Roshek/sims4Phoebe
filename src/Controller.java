
public class Controller {
	private Engine engine;
	private View view;
	
	public Controller(Engine x, View y){
		engine=x;
		view=y;
	}
	
	public void turnPassed(){
		engine.turnPassed();
		view.drawAll();
	}
	
	public void putOil(){
		engine.getActivePlayer().placeOil();
		view.drawAll();
	}
	
	public void putSlime(){
		engine.getActivePlayer().placeSlime();
		view.drawAll();
	}
	
	public void setArrow(int x, int y){
		engine.getArrow().calculateEndPoint(x, y);
		view.drawAll();
	}
}
