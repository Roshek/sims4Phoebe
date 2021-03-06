public class Startup {

	private int players_num=2;
	
	/**\brief Jatek inditasa
	 * 
	 * Latrehoz egy új engine-t,
	 * inicializalja es meghivja
	 * a play() fuggvenyet.
	 * 
	 */
	
	public void run() {					
		//System.out.println("->[:Startup].run()");
		
		Engine tmp=new Engine();
		tmp.init(players_num);
		
		tmp.play();
	}
	
	/**\brief Getter fgv
	 * 
	 * Visszadja a jatekosszamot.
	 * 
	 */
	public int getPlayers_num() {				
		//System.out.println("->[:Startup].getPlayers_num()");
		
		return this.players_num;
	}

	/**\brief Setter
	 * 
	 * Beallitja a jatekosszamot.
	 * 
	 * @param players_num
	 */
	public void setPlayers_num(int players_num) {			//KeSZ
		//System.out.println("->[:Startup].setPlayers_num(players_num)");
		
		this.players_num = players_num;
	}

}
