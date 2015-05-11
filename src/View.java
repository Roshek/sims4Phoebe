import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class View {

	// Attributumok inicializalasa
	private Engine engine;
	private Controller controller;
	private MainWindow window;
	
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
		this.setEngine(engine);
		this.controller = new Controller(engine, this);
		//JPanel?? TODO
		
		gMiniRobotList = new ArrayList<GraphicMiniRobot>();
		gRobotList = new ArrayList<GraphicRobot>();
		setgArrowList(new ArrayList<GraphicArrow>());
		gOilList = new ArrayList<GraphicOil>();
		gSlimeList = new ArrayList<GraphicSlime>();
		field = Resources.getMap();
		
		gArrow=new GraphicArrow(engine.getArrow());
		
	}
	
	public void updateGamePanel(){
		window.repaint();
	}
	
	public MainWindow getWindow() {
		return window;
	}
	public void setWindow(MainWindow window) {
		this.window = window;
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
	public void drawAll(Graphics g){
		drawField(g);
		for(GraphicOil gOil: gOilList){
			gOil.draw(g);
		}
		for(GraphicSlime gSlime: gSlimeList){
			gSlime.draw(g);
		}
		for(GraphicRobot gRobot: gRobotList){
			gRobot.draw(g);
		}
		for(GraphicMiniRobot gMiniRobot: gMiniRobotList){
			gMiniRobot.draw(g);
		}
		gArrow.draw(g);
	}
	
	/** A palya kirajzolasa
	 * Kirajzolja a palyat
	 * TODO: lehet mashova kene
	 */
	private void drawField(Graphics g){
		 g.drawImage(field, 0, 0, null);
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
	
	/* ============ GETTER SETTER METODUSOK =========
	 * 
	 */
	
	public Controller getController() {
		return controller;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public ArrayList<GraphicArrow> getgArrowList() {
		return gArrowList;
	}

	public void setgArrowList(ArrayList<GraphicArrow> gArrowList) {
		this.gArrowList = gArrowList;
	}

	
	
}
