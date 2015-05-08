public class Arrow {
	private Coord modifier;
	private Coord startPoint;
	private Coord endPoint;
	private int len = 40;

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
		double d = Coord.distance(startPoint, endPoint); 
		
		double ratio = len / d; 
		
		double xx = x-startPoint.getX(); 
		double yy = y-startPoint.getY();
		
		xx = (xx * ratio) + startPoint.getX(); 
		yy = (yy * ratio) + startPoint.getY();
		
		endPoint.setX((int)xx);
		endPoint.setY((int)yy);
		
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
