public class Coord {

	private int X; //vizszintes tengely
	private int Y; //fuggoleges tengely
	
	public Coord(){									//KeSZ
		//System.out.println("teszt");
		X=0; Y=0;
	}
	
	public Coord(Coord c){
		X = c.getX();
		Y = c.getY();
	}
	
	public Coord(int x, int y){							//KeSZ
		
		//System.out.println("branch");
		
		X=x; Y=y;
	}
	
	/**\brief Getter X
	 * 
	 * Visszaadja az X koordinatat.
	 */
	public int getX() {								//KeSZ
		//System.out.println("->[:Coord].getX");
		
		return X;
	}

	/**\brief Setter X
	 * 
	 * Beallitja a kapott ertek alapjan 
	 * az X koordinatat.
	 * 
	 * @param a
	 */
	public void setX(int a) {						//KeSZ
		//System.out.println("->[:Coord].setX(a)");
		X=a;
	}
	/**\brief Getter Y
	 * 
	 * Visszaadja az Y koordinatat.
	 */
	public int getY() {								//KeSZ
		//System.out.println("->[:Coord].getY()");
		return Y;
	}

	/**\brief Setter Y
	 * 
	 * Beallitja a kapott ertek alapjan 
	 * az Y koordinatat.
	 * 
	 * @param b
	 */
	public void setY(int b) {						//KeSZ
		//System.out.println("->[:Coord].setY(b)");
		
		Y=b;
	}

	/**\brief Ket vektor osszeadasa
	 * 
	 * osszeadja a ket megkapott vektort
	 * es visszater az uj Coord peldannyal.
	 * 
	 * @param pos1
	 * @param pos2
	 */
	public static Coord add(Coord pos1, Coord pos2) {		//KeSZ
		//System.out.println("->[:Coord].add(pos1, pos2)");
		
		Coord tmp=new Coord(pos1.getX()+pos2.getX(), pos1.getY()+pos2.getY());
		
		return tmp;
	}
	
	/**\brief Ket vektor tavolsaga
	 * 
	 * kiszamitja a ket megkapott vektor tavolsagat
	 * es visszater azzal.
	 * 
	 * @param pos1
	 * @param pos2
	 */
	public static double distance(Coord pos1, Coord pos2){
		return Math.sqrt((pos2.getX()-pos1.getX())*(pos2.getX()-pos1.getX()) + (pos2.getY()-pos1.getY())*(pos2.getY()-pos1.getY()));
	}
	
	public static double length(Coord pos){
		return Math.sqrt(pos.getX()*pos.getX()-pos.getY()*pos.getY());
	}

}
