
public class Slime extends Trap {
	
	private GraphicSlime gSlime;
	
	public Slime(){
		
		gSlime = new GraphicSlime(this);
		
	}
	
	/**\brief Megfelezi a robot modifier-et.
	 * 
	 * A kapott robot modifier-et elkeri 
	 * es megfelezi.
	 * 
	 * @param r
	 */
	
	//kesz
	public void spring(Robot r) {
		
		int x=r.getModifier().getX()/2;
		int y=r.getModifier().getY()/2;
		
		r.setModifier(new Coord(x, y));
		
		this.setUntilExpiration(this.getUntilExpiration()-1);
		
		if (this.getUntilExpiration() == 0)
			this.setExpired(true);
	}
	
	public void timePassed(){
	
	}

	public GraphicSlime getGSlime() {
		
		return gSlime;
	}

}