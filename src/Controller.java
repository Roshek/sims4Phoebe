
public class Controller {
	private Engine engine;
	private View view;
	
	public Controller(Engine x, View y){
		engine=x;
		view=y;
	}
	
	/**\brief Kor passzolasa
	 * 
	 * Meghivja az engine turnpassed()-jet és updateli utana a jatekteret
	 * 
	 */
	public void turnPassed(){
		engine.turnPassed();
		view.updateGamePanel();
	}
	
	/**\Olaj rakas
	 * 
	 * Elkeri az engine activeplayeret es meghivja arra a placeOil()-t, majd ujrarajzoltatja a jatekteret
	 * 
	 */
	public void putOil(){
		engine.getActivePlayer().placeOil();
		view.updateGamePanel();
	}
	
	/**\brief Slime rakas
	 * 
	 * Elkeri az engine activeplayeret es meghivja arra a placeSlime()-t, majd ujrarajzoltatja a jatekteret
	 * 
	 */
	public void putSlime(){
		engine.getActivePlayer().placeSlime();
		view.updateGamePanel();
	}
	
	/**\brief Nyil beallitasa
	 * 
	 * Elkeri az engine arrow-jat es kiszamoltatja annak vegpontjat az uj koordinatakra, majd ujrarajzoltatja a jatekteret
	 * 
	 */
	public void setArrow(int x, int y){
		engine.getArrow().calculateEndPoint(x, y);
		view.updateGamePanel();
	}
}
