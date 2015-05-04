import java.awt.image.BufferedImage;
import java.util.List;


public class View {

	private Engine engine;
	private Controller controller;
	protected Jpanel panel;
	
	private ArrayList<GraphicMiniRobot> gMiniRobotList;
	private ArrayList<GraphicRobot> gRobotList;
	private ArrayList<GraphicArrow> gArrowList;
	private ArrayList<GraphicOil> gOilList;
	private ArrayList<GraphicSlime> gSlimeList;
	private GraphicArrow gArrow;
	private BufferedImage field;
	
	/** A View osztaly konstruktora
	 * A parameterkent kapott enginet elraka,
	 * peldanyositja a controller osztalyt.
	 * @param engine
	 */
	public View(Engine engine){
		this.engine=engine;
		this.field=engine.map.getField();
		this.controller = new Controller();
		//JPanel?? TODO
	}
	
	/** Minden kirajzolasert felelos metodus
	 *  Vegigmegy a grafikus listakon es 
	 *  kirajzolja az elemeiket.
	 */
	public void drawAll(){
		
		
		
	}
	
	/** A palya kirajzolasa
	 * Kirajzolja a palyat
	 * TODO: lehet mashova kene
	 */
	private void drawField(){
		
	}
	
	/** Robot hozzaadasa
	 * Grafikus robot peldany hozzadasa a megfelelo listahoz
	 * @param gRobot
	 */
	public void robotAdded(GraphicRobot gRobot){
		gRobotList.Add(gRobot);
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
