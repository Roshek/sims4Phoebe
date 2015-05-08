
public class GraphicSlime implements Drawable{

	private Slime slime;
	
	public GraphicSlime(Slime slime){
		this.slime=slime;
	}
	
	public void draw() {
		if(!slime.getExpired() && slime != null){			//vizsgalat, hogy a ragacs a palyan kell, hogy legyen-e.
			//TODO
		}
		else{
			slime=null;									//ha mar nincs a palyan toroljuk a hivatkozast, GC dolgozhat
			return;
		}
		
	}

}
