
public class Oil extends Trap {
	
	public Oil(){								//KeSZ
	}	


	public void spring(Robot r) {
		
		r.setModifier(new Coord(0,0));
	}
	
	public void timePassed(){
		this.setUntilExpiration(this.getUntilExpiration()-1);
		
		if (this.getUntilExpiration() == 0)
			this.setExpired(true);
	}
}