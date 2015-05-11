
public class Oil extends Trap {
	
	private GraphicOil gOil;
	
	/** Oil konstruktor
	 * letrehozza a grafikus parjat
	 */
	public Oil(){								
		
		gOil = new GraphicOil(this);
		
	}	
	
	public GraphicOil getGOil(){
		return gOil;
	}
	
	/** Robottal torteno interakcio
	 * Az erintkezo robot aktualis modositovektorat kinullazza
	 */
	public void spring(Robot r) {
		
		r.setModifier(new Coord(0,0));
	}
	
	/** Ido csokkentese
	 * Idovel felszarad az olajfolt
	 */
	public void timePassed(){
		this.setUntilExpiration(this.getUntilExpiration()-1);
		
		if (this.getUntilExpiration() == 0)
			this.setExpired(true);
	}
}