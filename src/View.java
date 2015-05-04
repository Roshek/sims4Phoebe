import java.util.List;


public class View {

	private Engine engine;
	private Controller controller;
	protected Jpanel panel;
	
	private List<GraphicMiniRobot> gMiniRobotList;
	private List<GraphicRobot> gRobotList;
	private List<GraphicArrow> gArrowList;
	private List<GraphicOil> gOilList;
	private List<GraphicSlime> gSlimeList;
	private GraphicArrow gArrow;
	
	/** A View osztaly konstruktora
	 * A parameterkent kapott enginet elraka,
	 * peldanyositja a controller osztalyt.
	 * @param engine
	 */
	public View(Engine engine){
		this.engine=engine;
		this.controller = new Controller();
		//JPanel?? TODO
	}
	
	/** Minden kirajzolasert felelos metodus
	 *  Vegigmegy a grafikus listakon es 
	 *  kirajzolja az elemeiket.
	 */
	public void drawAll(){
		
		
		
	}
	
	/** Robot hozzaadasa
	 * Grafikus robot peldany hozzadasa a megfelelo listahoz
	 * @param gRobot
	 */
	public void robotAdded(GraphicRobot gRobot){
		
	}
	
	/** MiniRobot hozzaadasa
	 * Grafikus minirobot peldany hozzadasa a megfelelo listahoz
	 * @param gMiniRobot
	 */
	public void miniRobotAdded(GraphicMiniRobot gMiniRobot){
		
	}
	
	/** Olaj hozzaadasa
	 * Grafikus olaj peldany hozzadasa a megfelelo listahoz
	 * @param gOil
	 */
	public void oilAdded(GraphicOil gOil){
		
	}
	
	/** Ragacs hozzaadasa
	 * Grafikus ragacs peldany hozzadasa a megfelelo listahoz
	 */
	public void slimeAdded(GraphicSlime gSlime){
		
	}
	
	/** Nyil beallitasa
	 * ??? TODO
	 */
	public void setArrow(){
		
	}
	
	
}
