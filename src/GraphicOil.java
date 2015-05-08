import java.awt.image.BufferedImage;


public class GraphicOil implements Drawable{

	private Oil oil;
	private BufferedImage image;
	
	public GraphicOil(Oil oil){
		this.oil = oil;
		image = Resources.getOil();						// Resources osztalybol a megfelelo kep kinyerese
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
