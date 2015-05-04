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
		//JPanel??
	}
	
	public void drawAll(){
		
		
		
	}
	
	
	public void robotAdded(GraphicRobot gRobot){
		
	}
	
	public void miniRobotAdded(GraphicMiniRobot gMiniRobot){
		
	}
	
	public void oilAdded(GraphicOil gOil){
		
	}
	
	public void slimeAdded(GraphicSlime gSlime){
		
	}
	
	public void setArrow(){
		
	}
	
	
}
