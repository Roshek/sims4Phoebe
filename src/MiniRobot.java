public class MiniRobot extends Robot {

	private boolean onTrap=false;
	private GraphicMiniRobot gMiniRobot;
	
	public MiniRobot(Engine engine) {
		super(engine);
		setRadius(10);
		setImpulse(new Coord (0,0));
		

		
		
	}
	
	/** Grafikus p·r lÈtrehoz·sa
	 * Az ososztalyban levo metodus felulirasa
	 * letrehozza a grafikus part, majd hozzadja a view megfelelo listajahoz
	 */
	@Override
	public void createGraphicPair(){
		gMiniRobot = new GraphicMiniRobot(this);
		engine.view.miniRobotAdded(gMiniRobot);
	}

	/**\brief MiniRobot mesters√©ges intelligenci√°ja 
	 * 
	 * 20 hosszu lepesekkel el kezd ugralni a legkozelebbi
	 * Traphez a palya kozepvonalat kovetve
	 * 
	 * */
	
	public void move(){
		if (onTrap) //ha csapdan van, ne menjen sehova, takar√≠tsa csak fel.
			return;
		
		Coord trap = engine.getClosestTrap(position); 

		if(trap==null) //ha nincs csapda nem kell semmit sem tennie
			return;
		
		if (Coord.distance(getPosition(), trap) <= 20) setPosition(trap); //ha 20nal kozelebb van a traphez sim√°n csak r√°ugrik
		else {
			Map map = engine.getMap();
			
			if (Coord.distance(position, map.getClosesMidpoint(position)) > 5) {
				position = map.getClosesMidpoint(position);
				return;
			}
			position = map.getClosesMidpoint(position);
			position = map.getNextMidpoint(position, map.getMoveDir(position, trap));	
		}
	}
	
	/**\brief Egy robot ralep egy MiniRobotra 
	 * 
	 * Amikor egy Robot ralep a MiniRobotra a MiniRobot meghal
	 * es egy olajfolt marad a helyen
	 * 
	 * @param r A Robot ami ralepett
	 * 
	 * */
	
	public void steppedOnByRobot(Robot r){
		setAlive(false);
		placeOil();
	}
	
	/**\brief Egy Minirobot lep egy masik Minirobotra
	 * 
	 * Ha ket minirobot utkozik akkor a masik arrebb lep.
	 * 
	 * @param x A masik minirobot, aki ralepett this-re
	 * */
	
	public void steppedOnByMinirobot(MiniRobot x){
		this.setPosition(new Coord(this.getPosition().getX()+15,this.getPosition().getY()+15));
	}
	
	public void setOnTrap(boolean onTrap) {
		this.onTrap = onTrap;
	}
	
	public boolean isOnTrap() {
		return onTrap;
	}

	
}
