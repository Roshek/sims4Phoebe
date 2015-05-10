public class Arrow {
	private Coord modifier;				// Modifier vektorhoz
	private Coord startPoint;			// A nyil kezdeti pontja
	private Coord endPoint;				// A nyil végpontja (len tavolsagagra az aktiv robottol
	private int len = 40;				// A nyil hossza

	/** Konstruktor parameter nelkul
	 * Parameter nelkul a nyil vegpontja a kezdoponttol jobbra lesz
	 * A modifier is ugyan igy
	 */
	public Arrow(){
		setModifier(new Coord(len,0));	
		setStartPoint(new Coord(0,0));
		setEndPoint(new Coord(len,0));
	}
	
	/** Konstruktor parameterrel
	 * A megadott parameter fele fog mutatni a nyil
	 * 
	 * @param x
	 * @param y
	 */
	public Arrow(int x, int y){
		setModifier(new Coord(x+len,0));	
		setStartPoint(new Coord(x,y));
		setEndPoint(new Coord(x+len,y));
	}
	
	/** Nyil vegpontjanak szamitasa es beallitasa
	 * A kapott koordinatak alapjan, ezek fele fog mutatni a nyil
	 * 
	 * @param x
	 * @param y
	 */
	public void calculateEndPoint(int x, int y){
		double d = Coord.distance(startPoint, new Coord(x,y)); 		// A d tarolja a robot es a kattintas helye kozotti tavolsagot
		
		double ratio = len / d; 								// Tavolas aranya
		
		double xx = x-startPoint.getX(); 						// Az xx tarolja a nyil kezdetenek es az uj helyenek kulonbseget
		double yy = y-startPoint.getY();						// Az elozo y koordinatara
		
		xx = (xx * ratio) + startPoint.getX(); 					// Koordinata geometria magic
		yy = (yy * ratio) + startPoint.getY();					// Annyira nem magic, a tavolsagokat aranyositjuk es hozzavesszuk a nyil kezdopontjahoz

		
		
		endPoint.setX((int)xx);									// Vegpontok beallitasa
		endPoint.setY((int)yy);
		
		modifier.setX(endPoint.getX()-startPoint.getX());		// Modosito ertekek beallitasa
		modifier.setY(endPoint.getY()-startPoint.getY());
		
	};
	
	/** A nodifier szamitasa
	 * 
	 */
	public void calculateModifier(){
		modifier.setX(endPoint.getX()-startPoint.getX());
		modifier.setY(endPoint.getY()-startPoint.getY());
	};

	/** Modifier getter
	 * 
	 * @return
	 */
	public Coord getModifier() {
		return modifier;
	}

	/** Modifier setter
	 * 
	 * @param modifier
	 */
	public void setModifier(Coord modifier) {
		this.modifier = modifier;
	}

	public Coord getStartPoint() {
		return startPoint;
	}

	/** Kezdopont beallitasa
	 * Beallitja a parameterkent kapott kezdeti pontot,
	 * Vegpontnak pedig az alapertelmezettet
	 * @param startPoint
	 */
	public void setStartPoint(Coord startPoint) {
		this.endPoint = new Coord(startPoint.getX()+len,startPoint.getY());
		this.startPoint = startPoint;
		setModifier(new Coord(startPoint.getX()+len,0));	
	}

	public Coord getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Coord endPoint) {
		this.endPoint = endPoint;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}
	
	

}
