public class Arrow {
	private Coord modifier;
	private Coord startPoint;
	private Coord endPoint;

	public Arrow(){
		setModifier(new Coord(0,0));	
		setStartPoint(new Coord(0,0));
		setEndPoint(new Coord(0,0));
	}
	
	public Arrow(int x, int y){
		setModifier(new Coord(0,0));	
		setStartPoint(new Coord(x,y));
		setEndPoint(new Coord(0,0));
	}
	
	public void calculateEndPoint(int x, int y){
		
	};
	
	public void calculateModifier(int x, int y){
		
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
		this.startPoint = startPoint;
	}

	public Coord getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Coord endPoint) {
		this.endPoint = endPoint;
	}
	
	

}
