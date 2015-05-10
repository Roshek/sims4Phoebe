
public class Controller {
	private Engine engine;
	private View view;
	
	public Controller(Engine x, View y){
		engine=x;
		view=y;
	}
	
	public void turnPassed(){
		engine.turnPassed();
		view.updateGamePanel();
	}
	
	public void putOil(){
		engine.getActivePlayer().placeOil();
		view.updateGamePanel();
	}
	
	public void putSlime(){
		engine.getActivePlayer().placeSlime();
		view.updateGamePanel();
	}
	
	public void setArrow(int x, int y){
		engine.getArrow().calculateEndPoint(x, y);
		view.updateGamePanel();
	}
}
