public class Robot {

	private Coord position;
	private Coord impulse;
	private Coord modifier;
	private int oil_num;
	private int slime_num;
	private double road;
	private Boolean alive;
	private double radius = 20;
	private Engine engine;
	
	private int ID=0;
	/**\brief Robot konstruktor
	 * 
	 * Inicializalja a tarolt adatokat es 
	 * eltarolja a parameterkent kapott engine-t.
	 * 
	 * @param engine
	 */
	
	
	public Robot(Engine engine){
		
		System.out.println("->[:Robot].Robot(engine)");
		
		position=new Coord();
		modifier=new Coord();
		impulse=new Coord();
		
		oil_num=0;
		oil_num=0;
		road=0;
		alive=true;
		
		this.engine=engine;
	}
	
	/** \brief Kiszamolja a robot uj helyet es vektorait
	 * 
	 * Az impulzus es a modifier vektorok osszege
	 * lesz az uj impulse vektor, az uj hely pedig
	 * a hely es az uj impulse osszege.
	 * 
	 */
	
	public void calculateCoords() {					//KeSZ		
		impulse=Coord.add(impulse, modifier);
		position=Coord.add(position, impulse);
		modifier.setX(0);
		modifier.setY(0);
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
		}
	}
	
	public boolean collide(Coord pos){
		double sx = (pos.getX()-position.getX()) * (pos.getX()-position.getX());
		double sy= (pos.getY()-position.getY()) * (pos.getY()-position.getY());
		if (sx + sy <= (radius*radius)) return true;
		else return false;
	}
	
	public void steppedOnByRobot(Robot r){
		Coord cr = new Coord(r.getImpulse().getX(), r.getImpulse().getY());
		Coord ct = new Coord(impulse.getY(), impulse.getY());
		
		double lr = Math.sqrt(cr.getX() * cr.getX() + cr.getY() * cr.getY());
		double lt  = Math.sqrt(ct.getX() * ct.getX() + ct.getY() * ct.getY());
		
		if (lr < lt) {
			r.setAlive(false);
			
			cr.setX((int)(cr.getX()+ct.getX()*0.5));
			cr.setY((int)(cr.getY()+ct.getY()*0.5));
			
			this.setImpulse(cr);
		}
		else {
			this.setAlive(false);
			
			cr.setX((int)(cr.getX()+ct.getX()*0.5));
			cr.setY((int)(cr.getY()+ct.getY()*0.5));
			
			r.setImpulse(cr);
		}	
	}
	
	public void steppedOnByMiniRobot(MiniRobot x){
		Coord mx = new Coord(x.getModifier().getX(),x.getModifier().getY());
		
		if (mx.getX() < 0) mx.setX(mx.getX()+40);
		else mx.setX(mx.getX()-40);
		if (mx.getY() < 0) mx.setY(mx.getY()+40);
		else mx.setY(mx.getY()-40);
		
		x.setPosition(Coord.add(mx,x.getPosition()));
	}
	
	
	

	public Coord getPosition() {				//KeSZ
		System.out.println("Robot.getPosition()");
		
		return this.position;
	}

	/**
	 * 
	 * @param position
	 */
	public void setPosition(Coord position) {			//KeSZ
		System.out.println("->[:Robot].setPosition(position)");
		
		this.position = position;
	}

	public Coord getImpulse() {							//KeSZ
		System.out.println("->[:Robot].getImpluse()");
		
		return this.impulse;
	}

	/**
	 * 
	 * @param impulse
	 */
	public void setImpulse(Coord impulse) {				//KeSZ
		System.out.println("->[:Robot].setImpluse(impulse)");
		
		this.impulse = impulse;
	}

	public Coord getModifier() {						//KeSZ
		System.out.println("->[:Robot].getModifier()");
		
		return this.modifier;
	}

	/**
	 * 
	 * @param modifier
	 */
	public void setModifier(Coord modifier) {			//KeSZ
		System.out.println("->[:Robot].setModifier(modifier)");
		
		this.modifier = modifier;
	}

	public int getOil_num() {							//KeSZ
		System.out.println("->[:Robot].getOil_num()");
		
		return this.oil_num;
	}

	/**
	 * 
	 * @param oil_num
	 */
	public void setOil_num(int oil_num) {				//KeSZ
		System.out.println("->[:Robot].setOil_num(oil_num)");
		
		this.oil_num = oil_num;
	}

	public int getSlime_num() {							//KeSZ
		System.out.println("->[:Robot].getSlime_num()");
		
		return this.slime_num;
	}

	/**
	 * 
	 * @param slime_num
	 */
	public void setSlime_num(int slime_num) {			//KeSZ
		System.out.println("->[:Robot].setSlime_num(slime_num)");
		
		this.slime_num = slime_num;
	}

	public double getRoad() {							//KeSZ
		System.out.println("->[:Robot].getRoad()");
				
		return this.road;
	}

	/**
	 * 
	 * @param road
	 */
	public void setRoad(double road) {					//KeSZ
		System.out.println("->[:Robot].setRoad(road)");
		
		this.road = road;
	}

	public Boolean getAlive() {							//KeSZ
		System.out.println("->[:Robot].getAlive()");
		
		return this.alive;
	}

	/**
	 * 
	 * @param alive
	 */
	public void setAlive(Boolean alive) {				//KeSZ
		System.out.println("->[:Robot].setAlive(alive)");
		
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