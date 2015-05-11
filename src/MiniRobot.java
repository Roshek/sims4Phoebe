public class MiniRobot extends Robot {

	private boolean onTrap=false;
	private GraphicMiniRobot gMiniRobot;
	
	/** miniRobot konstruktora
	 * a robot konstruktorat hivja,
	 * plusz beallitja a sugarat 10-re
	 * @param engine
	 */
	public MiniRobot(Engine engine) {
		super(engine);
		setRadius(10);
		setImpulse(new Coord (0,0));

	}
	
	/** Grafikus pár létrehozása
	 * Az ososztalyban levo metodus felulirasa
	 * letrehozza a grafikus part, majd hozzadja a view megfelelo listajahoz
	 */
	@Override
	public void createGraphicPair(){
		gMiniRobot = new GraphicMiniRobot(this);
		engine.view.miniRobotAdded(gMiniRobot);
	}

	/**\brief MiniRobot mesterseges intelligenciaja 
	 * 
	 * 20 hosszu lepesekkel el kezd ugralni a legkozelebbi
	 * Traphez a palya kozepvonalat kovetve
	 * 
	 * */
	
	public void move(){
		if (onTrap) //ha csapdan van, ne menjen sehova, takarisa csak fel.
			return;
		
		Coord trap = engine.getClosestTrap(position);
		double trapDist = 0;
		
		if(trap==null) //ha nincs csapda nem kell semmit sem tennie
			return;
		trapDist = Coord.distance(position, trap);
		Map map = engine.getMap(); //elkeri a mapet
		
		//ha a trap kozelebb van mar, mintha ugrana meg egy midpointnyit, akkor ugorjon a trapre
		if (trapDist <= 70) {
			setPosition(trap);
		//System.out.println("MiniRobot:  Pos: " + position);
		}
		else {
			if (Coord.distance(position, map.getClosesMidpoint(position)) > 5) { // ha tul tavol van egy midpointtol
				position = map.getClosesMidpoint(position); //akkor raugrik es ez volt a lepese
				return;
			}
			position = map.getClosesMidpoint(position); //ha eleg kozel van akkor pedig mar a kovetkezore ugrik,
														//hogy ne tunjon ugy mintha egyhelyben allna
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
