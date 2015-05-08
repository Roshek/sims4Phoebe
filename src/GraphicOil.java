
public class GraphicOil implements Drawable{

	private Oil oil;
	
	public GraphicOil(Oil oil){
		this.oil = oil;
	}
	
	public void draw() {
		if(!oil.getExpired() && oil != null){			//vizsgalat, hogy az olaj a palyan kell, hogy legyen-e.
			//TODO
		}
		else{
			oil=null;									//ha mar nincs a palyan toroljuk a hivatkozast, GC dolgozhat
			return;
		}
	}

}
