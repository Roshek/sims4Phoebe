
public abstract class Trap {

	private Coord pos;
	private int r;
	private Robot owner;
	private int untilCleaned=2;
	private int untilExpiration=10;
	private boolean expired;

	// A leszarmazottak ebben valositjak meg az utkozesnel vegrehajtando muveleteket
	public abstract void spring(Robot r) ;
	

	public void timePassed() {};
	
	/** Utkozes detekcio
	 *  A parameterkent kapott koordinatarol eldonti
	 *  hogy utkozik-e az a robot poziciojaval
	 * 
	 * @param c
	 * @return
	 */
	public boolean collide(Coord c) {			
		if( ( c.getX()-pos.getX()) * (c.getX()-pos.getX() ) +
				( c.getY()-pos.getY()) * (c.getY()-pos.getY() ) <= r*r ){	//(x-x0)^2+(y-y0)^2<=R^2   
			return true;
		}
		return false;
	}
	
	/** miniRobot interakció
	 * ha a parameterkent atadott minirobot a csapdan all,
	 * a csapda visszaszamol, majd ha eltakaritodott elengedi a minirobotot
	 * @param x
	 */
	public void steppedOnByMiniRobot(MiniRobot x)
	{
		x.setOnTrap(true);
		this.beingCleaned();
		if (untilCleaned == 0) x.setOnTrap(false);
	}
	
	/** Takaritva vagyon
	 * a takaritasi idoig takaritas van.
	 */
	private void beingCleaned()
	{
		if (untilCleaned == 0)
			this.expired = true;
		else
			untilCleaned--;
	}

	
	/* ===== GETTER SETTER METODUSOK =====
	 * 
	 */
	
	public Coord getPos() {								
		return this.pos;
	}


	public void setPos(Coord pos) {				
		this.pos = pos;
	}

	public int getR() {	
		return this.r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public Robot getOwner() {
		return this.owner;
	}


	public void setOwner(Robot owner) {
		this.owner = owner;
	}

	public int getUntilCleaned() {
		return untilCleaned;
	}

	public void setUntilCleaned(int i) {
		this.untilCleaned = i;
	}

	public int getUntilExpiration() {
		return untilExpiration;
	}

	public void setUntilExpiration(int i) {
		this.untilExpiration = i;
	}


	public boolean getExpired() {
		return expired;
	}

	public void setExpired(boolean b) {
		this.expired = b;
	}

}
