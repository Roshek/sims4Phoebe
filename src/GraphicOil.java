import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class GraphicOil implements Drawable{

	private Oil oil;
	private BufferedImage image;
	
	public GraphicOil(Oil oil){
		this.oil = oil;
		image = Resources.getOil();						// Resources osztalybol a megfelelo kep kinyerese
	}
	
	public void draw(Graphics g) {
		if(!oil.getExpired() && oil != null){			//vizsgalat, hogy az olaj a palyan kell, hogy legyen-e.
			//pozicio kinyerese
			int x = oil.getPos().getX();
			int y = oil.getPos().getY();
			// eltolni kell, mert a drawimage a bal sarokhoz igazitja a kepet, nekunk meg a kozeppont van meg
			if ((x-22)>0) x=x-22; 
			if ((y-22)>0) y=y-22;
			//kirajzolas
			g.drawImage(image, x, y,null);
		}
		else{
			oil=null;									//ha mar nincs a palyan toroljuk a hivatkozast, GC dolgozhat
			return;
		}
	}

}
