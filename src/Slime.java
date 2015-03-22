public class Slime extends Trap {

	public Slime(){
		System.out.println("->[:Slime].Slime()");
	}
	
	/**\brief Megfelezi a robot modifier-�t.
	 * 
	 * A kapott robot modifier-�t elk�ri 
	 * �s megfelezi.
	 * 
	 * @param r
	 */
	public void spring(Robot r) {				//K�SZ
		System.out.println("->[:Slime].springSlime(r)");
		
		int x=r.getModifier().getX()/2;
		int y=r.getModifier().getY()/2;
		
		r.setModifier(new Coord(x, y));
	}

}