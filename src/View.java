import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class View {

	private Engine engine;
	private Controller controller;
	//protected Jpanel panel;
	
	private ArrayList<GraphicMiniRobot> gMiniRobotList;
	private ArrayList<GraphicRobot> gRobotList;
	private ArrayList<GraphicArrow> gArrowList;
	private ArrayList<GraphicOil> gOilList;
	private ArrayList<GraphicSlime> gSlimeList;
	private GraphicArrow gArrow;
	private BufferedImage field;
	
	/** A View osztaly konstruktora
	 * A parameterkent kapott enginet elrakja,
	 * peldanyositja a controller osztalyt,
	 * atveszi a maptol a palyat,
	 * peldanyositja a listakat.
	 * @param engine
	 */
	public View(Engine engine){
		this.engine=engine;
		this.field=engine.map.getField();
		//this.controller = new Controller();
		//JPanel?? TODO
		
		gMiniRobotList = new ArrayList<GraphicMiniRobot>();
		gRobotList = new ArrayList<GraphicRobot>();
		gArrowList = new ArrayList<GraphicArrow>();
		gOilList = new ArrayList<GraphicOil>();
		gSlimeList = new ArrayList<GraphicSlime>();
		
	}
	/** Setter a gArrowhoz
	 * @param gArrow
	 */
	public void setGArrow(GraphicArrow gArrow){
		this.gArrow = gArrow;
	}
	
	/** Setter a fieldhez
	 * @param field
	 */
	public void setField(BufferedImage field){
		this.field = field;
	}
	
	/** Minden kirajzolasert felelos metodus
	 *  Vegigmegy a grafikus listakon es 
	 *  kirajzolja az elemeiket.
	 */
	public void drawAll(){
		
		for(GraphicOil gOil: gOilList){
			gOil.draw();
		}
		for(GraphicSlime gSlime: gSlimeList){
			gSlime.draw();
		}
		for(GraphicRobot gRobot: gRobotList){
			gRobot.draw();
		}
		for(GraphicMiniRobot gMiniRobot: gMiniRobotList){
			gMiniRobot.draw();
		}
		gArrow.draw();
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
		gRobotList.add(gRobot);
	}
	
	/** MiniRobot hozzaadasa
	 * Grafikus minirobot peldany hozzadasa a megfelelo listahoz
	 * @param gMiniRobot
	 */
	public void miniRobotAdded(GraphicMiniRobot gMiniRobot){
		gMiniRobotList.add(gMiniRobot);
	}
	
	/** Olaj hozzaadasa
	 * Grafikus olaj peldany hozzadasa a megfelelo listahoz
	 * @param gOil
	 */
	public void oilAdded(GraphicOil gOil){
		gOilList.add(gOil);
	}
	
	/** Ragacs hozzaadasa
	 * Grafikus ragacs peldany hozzadasa a megfelelo listahoz
	 */
	public void slimeAdded(GraphicSlime gSlime){
		gSlimeList.add(gSlime);
	}
	
	/** Nyil beallitasa
	 * ??? TODO
	 */
	public void setArrow(){
		
	}
	
	
}
