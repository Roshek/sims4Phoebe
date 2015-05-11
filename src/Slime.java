
public class Slime extends Trap {
	
	private GraphicSlime gSlime;
	
	/** Slime konstruktora
	 * letrehozza a grafikus parjat.
	 */
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
	
	public void spring(Robot r) {
		
		int x=r.getModifier().getX()/2;
		int y=r.getModifier().getY()/2;
		
		r.setModifier(new Coord(x, y));
		
		this.setUntilExpiration(this.getUntilExpiration()-1);
		
		if (this.getUntilExpiration() == 0)
			this.setExpired(true);
	}
	
	/** Ido mulasa
	 * A ragacsra nincs hatassal az ido mulasa
	 */
	public void timePassed(){
	
	}
	
	/** grafikus par gettere
	 * 
	 * @return
	 */
	public GraphicSlime getGSlime() {
		
		return gSlime;
	}

}