public class Oil extends Trap {
	
	public Oil(){								//K�SZ
		System.out.println("->[:Oil].Oil()");
	}	

	/**\brief Kinull�zza a robot modifier�t.
	 * 
	 * A kapott robot modifier�t null�ra �ll�tja.
	 * 
	 * @param r
	 */
	public void spring(Robot r) {				//K�SZ
		System.out.println("->[:Oil].springOil(r)");
		
		r.setModifier(new Coord(0,0));
	}

}