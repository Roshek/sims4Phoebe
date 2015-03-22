public class Startup {

	private int players_num=2;
	
	/**\brief J�t�k ind�t�sa
	 * 
	 * L�trehoz egy �j engine-t,
	 * inicializ�lja �s megh�vja
	 * a play() f�ggv�ny�t.
	 * 
	 */
	
	public void run() {						//K�SZ not sure about this
		System.out.println("->[:Startup].run()");
		
		Engine tmp=new Engine();
		tmp.init(players_num);
		
		tmp.play();
	}

	public int getPlayers_num() {				//K�SZ
		System.out.println("->[:Startup].getPlayers_num()");
		
		return this.players_num;
	}

	/**
	 * 
	 * @param players_num
	 */
	public void setPlayers_num(int players_num) {			//K�SZ
		System.out.println("->[:Startup].setPlayers_num(players_num)");
		
		this.players_num = players_num;
	}

}