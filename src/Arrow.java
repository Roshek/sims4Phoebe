public class Arrow {
	private Coord modifier;				// 
	private Coord startPoint;			// A nyil kezdeti pontja
	private Coord endPoint;				// A nyil végpontja (len tavolsagagra az aktiv robottol
	private int len = 40;				// A nyil hossza

	public Arrow(){
		setModifier(new Coord(0,0));	
		setStartPoint(new Coord(0,0));
		setEndPoint(new Coord(len,0));
	}
	
	public Arrow(int x, int y){
		setModifier(new Coord(0,0));	
		setStartPoint(new Coord(x,y));
		setEndPoint(new Coord(x+len,y));
	}
	
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
	
	public void calculateModifier(){
		modifier.setX(endPoint.getX()-startPoint.getX());
		modifier.setY(endPoint.getY()-startPoint.getY());
	};

	public Coord getModifier() {
		return modifier;
	}

	public void setModifier(Coord modifier) {
		this.modifier = modifier;
	}

	public Coord getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Coord startPoint) {
		this.endPoint = new Coord(startPoint.getX()+len,startPoint.getY());
		this.startPoint = startPoint;
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
