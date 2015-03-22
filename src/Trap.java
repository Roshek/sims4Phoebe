public class Trap {

	private Coord pos;
	private int r;
	private Robot owner;

	/**
	 * 
	 * @param r
	 */
	public void spring(Robot r) {				//K�SZ
		System.out.println("->[:Trap].springTrap(r)");
	}

	/**\brief �tk�zik-e a robot a csapd�val
	 * 
	 * Megn�zi, hogy a kapott koordin�ta
	 * beleesik-e a csapda k�r�li r sugar�
	 * k�rbe �s ennek megfelel�en
	 * visszat�r egy bool-lal.
	 * 
	 * @param c
	 */
	public Boolean collide(Coord c) {			//K�SZ
		System.out.println("->[:Trap].collide(c)");
		
		if((c.getX()-pos.getX())*(c.getX()-pos.getX())+(c.getY()-pos.getY())*(c.getY()-pos.getY())<=r*r){		//(x-x0)^2+(y-y0)^2<=R^2
			return true;
		}
		
		return false;
	}

	public Coord getPos() {						//K�SZ
		System.out.println("->[:Trap].getPos()");	
		
		return this.pos;
	}

	/**
	 * 
	 * @param pos
	 */
	public void setPos(Coord pos) {				//K�SZ
		System.out.println("->[:Trap].setPos(pos)");
		
		this.pos = pos;
	}

	public int getR() {							//K�SZ
		System.out.println("->[:Trap].getR()");
		
		return this.r;
	}

	/**
	 * 
	 * @param r
	 */
	public void setR(int r) {					//K�SZ
		System.out.println("->[:Trap].setR(r)");
		
		this.r = r;
	}

	public Robot getOwner() {					//K�SZ
		System.out.println("->[:Trap].getOwner()");
		
		return this.owner;
	}

	/**
	 * 
	 * @param owner
	 */
	public void setOwner(Robot owner) {				//K�SZ
		System.out.println("->[:Trap].setOwner(owner)");
		
		this.owner = owner;
	}

}