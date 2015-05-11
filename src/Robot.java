public class Robot {

	protected Coord position;
	protected Coord impulse;
	protected Coord modifier;
	protected int oil_num;
	protected int slime_num;
	protected double road;
	protected Boolean alive=true;
	protected double radius = 20;
	protected Engine engine;
	private GraphicRobot gRobot;
	
	protected int ID=0;
	/**\brief Robot konstruktor
	 * 
	 * Inicializalja a tarolt adatokat es 
	 * eltarolja a parameterkent kapott engine-t.
	 * 
	 * @param engine
	 */
	
	
	public Robot(Engine engine){

		
		position=new Coord();
		modifier=new Coord();
		impulse=new Coord();
		
		oil_num=5;
		slime_num=5;
		road=0;
		alive=true;
		
		this.engine=engine;
		
		//Grafikus par letrehozasa
		createGraphicPair();
	}
	
	/** Grafikus pár létrehozása
	 * letrehozza a grafikus part, majd hozzadja a view megfelelo listajahoz
	 */
	public void createGraphicPair(){
		gRobot = new GraphicRobot(this);
		engine.view.robotAdded(gRobot);		//ez itt így elég szornyu
	}
	
	
	/** \brief Kiszamolja a robot uj helyet es vektorait
	 * 
	 * Az impulzus es a modifier vektorok osszege
	 * lesz az uj impulse vektor, az uj hely pedig
	 * a hely es az uj impulse osszege.
	 * 
	 */
	
	public void calculateCoords() {					//KeSZ

		//impulse=Coord.add(impulse, modifier);
		impulse=modifier;							//DEBUGOLÁSHOZ TO BE REROLLED
		Coord oldpos= position;
		
		if(impulse.getX()==0 && impulse.getY()==0)
			System.out.println("Nulla a változás O.o");
		
		position=Coord.add(position, impulse);
		road+=Coord.distance(oldpos, position);
		modifier.setX(0);
		modifier.setY(0);
		
		System.out.println("\nÚj koords: " + position.getX() + " " + position.getY() + " régi koords: " + oldpos.getX() + " " + oldpos.getY());
	}

	public boolean isAlive() {							//KeSZ	
		return alive;
	}

	/**\brief Lerak egy olajat
	 * 
	 * Ha van meg renkelkezesre allo olaj,
	 * akkor letrehoz egyet a jelenlegi
	 * helyen es berakja az engine-be.
	 */
	public void placeOil() {		//KeSZ	
		if(oil_num>0){
			oil_num--;
			
			Oil tmp=new Oil();		
			tmp.setPos(position);
			tmp.setOwner(this);
			
			engine.addTrap(tmp);
			engine.view.oilAdded(tmp.getGOil());
		}
	}

	/**\brief Lerak egy ragacsot
	 * 
	 * Ha van meg renkelkezesre allo ragacs,
	 * akkor letrehoz egyet a jelenlegi
	 * helyen es berakja az engine-be.
	 */
	public void placeSlime() {			//KeSZ	
		if(slime_num>0){
			slime_num--;
			
			Slime tmp=new Slime();		
			tmp.setPos(position);
			tmp.setOwner(this);
			
			engine.addTrap(tmp);
			engine.view.slimeAdded(tmp.getGSlime());
		}
	}
	
	/**\brief Valami utkozik-e a robottal 
	 * 
	 * Megnezi, hogy a kapott pozicio belul van e a roboton
	 * 
	 * @param pos a kapott pozicio
	 * 
	 * @return igaz ha utkozott
	 * */
	
	public boolean collide(Coord pos){
		double sx = (pos.getX()-position.getX()) * (pos.getX()-position.getX());
		double sy= (pos.getY()-position.getY()) * (pos.getY()-position.getY());
		if (sx + sy <= (radius*radius)) return true;
		else return false;
	}
	
	
	/**\brief Robot utkozik Robottal
	 * 
	 * Ha ket robot utkozik, akkor a lassabb meghal,
	 * a masik pedig ketejuk atlagsebessegevel halad tova
	 *  
	 *  @param r A masik Robot
	 *  */
	public void steppedOnByRobot(Robot r){
		Coord cr = new Coord(r.getImpulse().getX(), r.getImpulse().getY()); //a ket robot impulzusa
		Coord ct = new Coord(impulse.getX(), impulse.getY());
		
		double lr = Math.sqrt(cr.getX() * cr.getX() + cr.getY() * cr.getY()); //a ket impulzus hossza
		double lt  = Math.sqrt(ct.getX() * ct.getX() + ct.getY() * ct.getY()); //(erre lehetne hasznalni a lengthet is mar)
		
		if(lr==lt && lr==0)		//ha mindketten ï¿½llnak akkor hadd acsorogjanak tovabb
			return;
		
		if (lr < lt) { //ha a masik volt a lasabb
			r.setAlive(false); //meghal
			r.setImpulse(new Coord(0,0)); //nagyon
			cr.setX((int)(cr.getX()+ct.getX()*0.5)); //this pedig megkapja az atlagsebesseget
			cr.setY((int)(cr.getY()+ct.getY()*0.5));
			
			this.setImpulse(cr);
		}
		else {
			//ha this volt a lasabb akkor pont forditva tortenik minden
			this.setAlive(false);
			this.setImpulse(new Coord(0,0));
			cr.setX((int)(cr.getX()+ct.getX()*0.5));
			cr.setY((int)(cr.getY()+ct.getY()*0.5));
			
			r.setImpulse(cr);
		}	
	}
	
	/**\brief MiniRobot ralep egy Robotra
	 * 
	 * Ha egy minirobot ralep egy robotra
	 * akkor a minirobot arrebb ugrik
	 * 
	 * @param x A MiniRobot
	 * */
	
	public void steppedOnByMiniRobot(MiniRobot x){
		//egyszeruen csak haladasi iranytoil fuggoen arrebb ugrik 40et
		Coord mx = new Coord(x.getModifier().getX(),x.getModifier().getY());
		if (mx.getX() < 0) mx.setX(mx.getX()+40);
		else mx.setX(mx.getX()-40);
		if (mx.getY() < 0) mx.setY(mx.getY()+40);
		else mx.setY(mx.getY()-40);
		
		x.setPosition(Coord.add(mx,x.getPosition()));
	}
	
	
	

	public Coord getPosition() {				//KeSZ
		
		
		return this.position;
	}

	/**
	 * 
	 * @param position
	 */
	public void setPosition(Coord position) {			//KeSZ
		
		
		this.position = position;
	}

	public Coord getImpulse() {							//KeSZ
		
		
		return this.impulse;
	}

	/**
	 * 
	 * @param impulse
	 */
	public void setImpulse(Coord impulse) {				//KeSZ
		
		
		this.impulse = impulse;
	}

	public Coord getModifier() {						//KeSZ
		
		
		return this.modifier;
	}

	/**
	 * 
	 * @param modifier
	 */
	public void setModifier(Coord modifier) {			//KeSZ
		
		
		this.modifier = modifier;
	}

	public int getOil_num() {							//KeSZ
		
		
		return this.oil_num;
	}

	/**
	 * 
	 * @param oil_num
	 */
	public void setOil_num(int oil_num) {				//KeSZ
		
		
		this.oil_num = oil_num;
	}

	public int getSlime_num() {							//KeSZ
		
		
		return this.slime_num;
	}

	/**
	 * 
	 * @param slime_num
	 */
	public void setSlime_num(int slime_num) {			//KeSZ
		
		this.slime_num = slime_num;
	}

	public double getRoad() {							//KeSZ
		
				
		return this.road;
	}

	/**
	 * 
	 * @param road
	 */
	public void setRoad(double road) {					//KeSZ
		
		
		this.road = road;
	}

	public Boolean getAlive() {							//KeSZ
		
		
		return this.alive;
	}

	/**
	 * 
	 * @param alive
	 */
	public void setAlive(Boolean alive) {				//KeSZ
		
		
		this.alive = alive;
	}

	public void setID(int i){
		ID=i;
	}
	
	public int getID(){
		return ID;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public Engine getEngine(){
		return this.engine;
	}
}