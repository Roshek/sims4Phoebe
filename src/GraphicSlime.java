import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class GraphicSlime implements Drawable{

	private Slime slime;
	private BufferedImage image;
	private Graphics g;

	
	public GraphicSlime(Slime slime){
		this.slime=slime;
		image = Resources.getSlime();	// Resources osztalybol a megfelelo kep kinyerese
	}
	
	public void draw() {
		if(!slime.getExpired() && slime != null){ //vizsgalat, hogy a ragacs a palyan kell, hogy legyen-e.
			//pozicio kinyerese
			int x = slime.getPos().getX();
			int y = slime.getPos().getY();
			// eltolni kell, mert a drawimage a bal sarokhoz igazitja a kepet, nekunk meg a kozeppont van meg
			if ((x-22)>0) x=x-22; 
			if ((y-22)>0) y=y-22;
			//kirajzolas
			g.drawImage(image, x, y,null);
		}
		else{
			slime=null;	//ha mar nincs a palyan toroljuk a hivatkozast, GC dolgozhat
			return;
		}
		
	}

}
