public class MiniRobot extends Robot {

	private boolean onTrap=false;
	
	public MiniRobot(Engine engine) {
		super(engine);
		setRadius(10);
		setImpulse(new Coord (0,0));
		
	}

	/**\brief MiniRobot mesterséges intelligenciája 
	 * 
	 * 20 hosszu lepesekkel el kezd ugralni a legkozelebbi
	 * Traphez a palyan nyilegyenesen
	 * 
	 * */
	
	public void move(){
		if (onTrap) //ha csapdan van, ne menjen sehova, takarítsa csak fel.
			return;
		
		Coord trap = engine.getClosestTrap(position); 

		if(trap==null) //ha nincs csapda nem kell semmit sem tennie
			return;
		
		if (Coord.distance(getPosition(), trap) <= 20) setPosition(trap); //ha 20nal kozelebb van a traphez simán csak ráugrik
		else {
			//hasonlo haromszogekkel egyszeruen kiszamolja
			//merre kell ugralnia
			//az egyik haromszog a MiniRobot es a Trap pozicioja kozott feszülo helyvektor
			//a masik az a vektor ami a MiniRobot ugrasanak vegpontjaba mutat, szinten helyvektorkent
			
			double d = Coord.distance(getPosition(), trap); //a MiniRobot es a csapda pozicioja kozotti tavolsag
			
			double ratio = d / 20.0; //a tavolsag leosztva az ugras hosszaval, igy meg van az arany a haromszogek kozott
			ratio = 1 / ratio; //ennek a reciproka kell nekunk
			
			double x = trap.getX()-getPosition().getX(); //helyvektort csinalunk
			double y = trap.getY()-getPosition().getY();
			
			x = x * ratio; //majd leroviditjuk annyira a ket koordinatajat amennyire a MiniRobotnak mozdulnia kell
			y = y * ratio;
			
			setPosition(new Coord ((int)(x + position.getX()),(int)(y + position.getY()))); //megadjuk az uj poziciot, ami a regi eltolva xy-al
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
	
	/*Ez elvileg nem kell*/
	
	/*
	public Coord getClosestTrap(Coord position) {
		ArrayList<Trap> lt = getEngine().getTraps();
		Trap clsst = null;
		try
		{
			for (Trap it: lt) {
				if (Coord.distance(getPosition(), it.getPos()) < Coord.distance(getPosition(), clsst.getPos())) {
					clsst = it;
				}
			}
		
			return clsst.getPos();
		}
		catch (NullPointerException ex)
		{
			return null;
		}
	}
	*/
	
	public void setOnTrap(boolean onTrap) {
		this.onTrap = onTrap;
	}
	
	public boolean isOnTrap() {
		return onTrap;
	}

	
}
