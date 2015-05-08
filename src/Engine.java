import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Engine {

	private int player_num;
	private int round_num;
	public ArrayList<Robot> alivePlayers;			//innentol priv lenne
	public ArrayList<Robot> deadPlayers;
	public ArrayList<Trap> traps;
	
	public ArrayList<MiniRobot> miniRobots;
	
	public Map map;
	public Robot activePlayer;						//eddig
	private Robot winner;
	
	//PETI M”DOSÌT¡SAI 2015.05.08.
	
	private Arrow arrow;
	
	public Arrow getArrow(){
		return arrow;
	}
	
	public Robot getActivePlayer(){
		return activePlayer;
	}
	
	//PETI V…GE
	
	//2015.04.20.
	
	private int RobotID=1;
	
	public View view;
	
	
	private void moveminiRobots(){				//DONE
		for(MiniRobot x : miniRobots){
			x.move();
			Coord place=x.getPosition();
			boolean dead=map.fall(place);
			if(dead){
				x.setAlive(false);
			}
		}
	}
	
	public Coord getClosestTrap(Coord pos){		//DONE
		if(!traps.isEmpty()){
			Trap tmp=traps.get(0);
			for(Trap x: traps){
				if(Coord.distance(pos,tmp.getPos())<Coord.distance(pos, x.getPos())){
					tmp=x;
				}
			}
			return tmp.getPos();
		}
		return null;
	}
	
	private void testMiniRobotForTraps(){
		for(MiniRobot x: miniRobots){
			Coord place=x.getPosition();
			
			for(Trap i: traps){
				if(i.collide(place)){
					i.steppedOnByMiniRobot(x);
				}
			}
			for(Robot i: alivePlayers){
				if(i.collide(place))
					i.steppedOnByMiniRobot(x);
			}
			for(MiniRobot i: miniRobots){
				if(i.collide(place) && (i.getID()!=x.getID()))  //vegigmegy egy minirobot az osszes miniroboton, ezert ki kell zarni, hogy magat is vizsgalja
					i.steppedOnByMinirobot(x);
			}
		}
	}
	
	//UToLAGOS FuGGVeNYEK
	
	/**\brief Nincs tobb jatekos
	 * 
	 * Minden jatekos meghalt, 
	 * kijelzi a kepernyore es 
	 * kilep a jatekbol.
	 * 
	 */
	
	public void allPlayersDead(){
		//System.out.println("->[:Engine].allPlayersDead()");
		
		System.out.println("\nMinenki leesett, nincs gyoztes.\n");
		}
	
	
	/**\brief Kilepes
	 * 
	 * Bezarja a programot.
	 * 
	 */
	
	public void quit(){
		//System.out.println("->[:Engine].quit()");
		
		System.exit(0);
	}
	
	
	/**\brief Robotok mozgatasa
	 * 
	 * Minden meg elo robotra meghÔøΩvja a
	 * calCulateCoords() fuggvenyt, es 
	 * ellenorzi, hogy leesett-e valamelyik.
	 * Ha igen, akkor azt atteszi a halott 
	 * robotok koze.
	 */
	
	private void moveRobots(){					//KeSZ				///VALoSZÔøΩNÔøΩLEG HIBaS
		
		//System.out.println("->[:Engine].moveRobots()");
		
		int numberOfRobotsAtStartOfCycle=alivePlayers.size();
		
		for(Robot tmp: alivePlayers){
			tmp.calculateCoords();
			Coord newpos=tmp.getPosition();
			if(map.fall(newpos)){
				System.out.println("I fell off fuck me!");
				tmp.setAlive(false);
			}
		}
	}
	
	/**\brief Robotok ellenorzese a csapdakra
	 * 
	 * Egyesevel elkeri a robotoktol
	 * a helyuket, es ellenorizteti az
	 * osszes csapdaval, hogy belelepett-e.
	 * Ha igen, akkor atadja a csapdanak
	 * a robotot, hogy az beallÔøΩtsa a 
	 * neki megfelelo modosÔøΩtasokat.
	 * 
	 */
	
	private void trapRobots(){					//KeSZ				//MoDOSULT
		
		//System.out.println("->[:Engine].trapRobots()");
		
		for(Robot robot: alivePlayers){
			Coord pos=robot.getPosition();
			
			for(Trap trap: traps){
				if(trap.collide(pos)){
					trap.spring(robot);
				}
			}
			
			for(MiniRobot x:miniRobots){
				if(x.collide(pos)){
					x.steppedOnByRobot(robot);
				}
			}
			
			for(Robot x:alivePlayers){
				if(x!=robot){
					if(x.collide(pos)){
						System.out.println("I STEPPED ON YOUR DICK");
						x.steppedOnByRobot(robot);
					}
				}
			}
		}
		//System.out.println("<-[:Engine].trapRobots()");
	}
	
	/**\brief Kor vege
	 * 
	 * Amikor az utolso jatekos is elpasszolta
	 * a koret, akkor hÔøΩvodik meg. MeghÔøΩvja a
	 * az engine trapRobots() es moveRobots()
	 * fuggvenyeit ilyen sorrendben.
	 * 
	 */
	
	public void roundOver(){					//KeSZ		//csak a szkeletonhoz public
		
		//System.out.println("->[:Engine]roundOver()");
		
		trapRobots();
		moveRobots();
		
		moveminiRobots();
		testMiniRobotForTraps();
			
		Iterator<Robot> it=alivePlayers.iterator();				//TAKARITAS
		while(it.hasNext()){
			Robot x=it.next();
			if(!x.isAlive()){
				System.out.println("I SHAMELESSLY RAPED A FELLOW ROBOT :(" + x.getPosition().getX() + " " + x.getPosition().getY());
				System.out.println(x.isAlive());
				deadPlayers.add(x);
				it.remove();
			}
		}
		
		Iterator<Trap> it2=traps.iterator();
		while(it2.hasNext()){
			Trap x=it2.next();
			if(x.getExpired())
				it2.remove();
		}
		
		Iterator<MiniRobot> it3=miniRobots.iterator();
		while(it3.hasNext()){
			MiniRobot x=it3.next();
			if(!x.isAlive()){
				System.out.println("I BRUTALLY MURDERED A DEFENSELESS MINIBOT :(");
				System.out.println(x.getPosition().getX() + " " + x.getPosition().getY());
				it3.remove();
			}
		}
		
		for(Trap i:traps){
			i.timePassed();
		}
		round_num--;
		
		//System.out.println("<-[:Engine].roundOver()");
	}
	
	//PUBLIC, TERVEZETT FuGGVeNYEK
	
	/**\brief Engine konstruktor
	 * 
	 * Inicializalja az ArrayList-eket es
	 * beallÔøΩtja a max korok szamat. 
	 */
	
	public Engine(){							//KeSZ
		
		//System.out.println("->[:Engine]Engine()");
		
		alivePlayers=new ArrayList<Robot>();
		deadPlayers=new ArrayList<Robot>();
		miniRobots=new ArrayList<MiniRobot>();
		traps=new ArrayList<Trap>();
		map=new Map();
		
		player_num=0;
		round_num=30;
		
		arrow=new Arrow();
	}
	
	/**\brief A fo playfuggveny, itt fut a jatek nagy resze
	 * 
	 * AmÔøΩg a korszamlalo el nem eri a nullat
	 * egyesevel vegigmegy az elo robotokon,
	 * majd var, amÔøΩg a kezelofelulet felebreszti
	 * a kor atpasszolasaval. Amikor vegig ert a 
	 * a robotokon meghÔøΩvja a roundOver() fuggvenyt.
	 * Amikor elfogytak a korok meghÔøΩvja a whoWins()
	 * fuggvenyt.
	 */
	
	public void play() {						//PARTIALLY READY
		
		//System.out.println("->[:Engine].play()");
		
		while(round_num>0){
			for(Robot tmp: alivePlayers){
				activePlayer=tmp;
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("Engine.play() Wait error ._.");
				}
			}
			roundOver();
			if(alivePlayers.isEmpty()){
				allPlayersDead();
			}
		}
		
		whoWins();
	}

	/**\brief Jatek inicializalasa
	 * 
	 * Latrehozza es betolti a palyat kezelo
	 * objektumot, letrehoz a parametereben
	 * kapott szamu robotot es lerakja oket a
	 * palyara.
	 * 
	 * @param numberOfPlayers
	 */
	public void init(int numberOfPlayers) {			//KeSZ
		
		//System.out.println("->[:Engine].init(numberOfPlayers)");
		
		map.load("tesztpalya.bmp");
		view = new View(this);
		for(int i=0;i<numberOfPlayers;i++){
			Robot tmp=new Robot(this);
			alivePlayers.add(tmp);
			tmp.setID(RobotID);
			RobotID++;
		}
		
		ArrayList<Coord> tmp=map.putPlayers(numberOfPlayers);
		
		for(int i=0;i<numberOfPlayers;i++){
			//alivePlayers.get(i).setPosition(tmp.get(i));		//nem valid amÔøΩg nincs putPlayers
		}
	}

	/**\brief Kor passzolasa
	 * 
	 * A kezelofelulettol kapott vektort
	 * atadja az epp aktÔøΩv robotnak az
	 * uj modifierekent, es felebreszti az
	 * Engine.play()-ben varakozo foszalat,
	 * hogy tovabblepjen a jatek a kovetkezo
	 * jatekosra.
	 * 
	 * @param modifier
	 */
	public void turnPassed() {		//TO BE REVIEWED				//MODIFIED
		
		//System.out.println("->[:Engine].turnPassed(modifier)");
		
		activePlayer.setModifier(arrow.getModifier());
		
		notifyAll();			//Szkeletonhoz nem kell
		
//		int index=alivePlayers.indexOf(activePlayer);
//		Robot newActivePlayer=alivePlayers.get((index+1)%alivePlayers.size());		//O.o
//		
//		activePlayer=newActivePlayer;
//		
//		//activePlayer=alivePlayers.get((alivePlayers.indexOf(activePlayer)+1)%alivePlayers.size());
	}

	/**\brief uj csapda hozzaadasa
	 * 
	 * Eltarolja a csapdak kozott a
	 * parameterben kapott csapdat.
	 * 
	 * @param x
	 */
	public void addTrap(Trap x) {					//KeSZ
				
		//System.out.println("->[:Engine].addTrap(x)");
		
		traps.add(x);
	}

	/**\brief atrakja a robotot a halottak koze
	 * 
	 * A parameterben kapott robotot kiveszi az
	 * elok kozul es atteszi a halottak koze.
	 * A robot alive flagjat is atallÔøΩtja.
	 * 
	 * @param r
	 */
	public void dieRobot(Robot r) {					//KeSZ					//csak a szkeletonban public
		
		//System.out.println("->[:Engine].dieRobot(r)");
		r.setAlive(false);
	}

	/**\brief Megnezi ki nyert
	 * 
	 * Vegignezi a robotok road attributumat
	 * es kivalasztja a legnagyobbat.
	 * Ezt eltarolja a winner attributumban.
	 *  
	 */
	
	public void whoWins() {							//KeSZ					//csak a szkeletonban public
		
		//System.out.println("->[:Engine].whoWins()");
		
		Robot winningPlayer=new Robot(this);
		for(Robot tmp: alivePlayers){
			if(tmp.getRoad()>winningPlayer.getRoad()){ winningPlayer=tmp;}
		}
		
		winner=winningPlayer;
	}

	public int getPlayer_num() {					//KeSZ
		//System.out.println("->[:Engine].getPlayer_num()");
		
		return this.player_num;
	}

	/**
	 * 
	 * @param player_num
	 */
	public void setPlayer_num(int player_num) {		//KeSZ
		
		//System.out.println("->[:Engine].setPlayer_num(player_num)");
		
		this.player_num = player_num;
	}

	public int getRound_num() {						//KeSZ
		
		//System.out.println("->[:Engine].getRound_num()");
		
		return this.round_num;
	}

	/**
	 * 
	 * @param round_num
	 */
	public void setRound_num(int round_num) {		//KeSZ
		
		//System.out.println("->[:Engine].setRound_num(numberOfRounds)");
		
		this.round_num = round_num;
	}

//	public ArrayList<Robot> getPlayers() {			//Ez szerintem nem fog kelleni
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 * 
//	 * @param players
//	 */
//	public void setPlayers(ArrayList<Robot> players) {
//		throw new UnsupportedOperationException();
//	}
	
	
	

	public ArrayList<Trap> getTraps() {				//KeSZ
		
		//System.out.println("->[:Engine].getTraps()");
		
		return this.traps;
	}

	/**
	 * 
	 * @param traps
	 */
	public void setTraps(ArrayList<Trap> traps) {	//KeSZ
		
		//System.out.println("->[:Engine].setTraps(traps)");
		
		this.traps = traps;
	}
	
	public void setActivePlayer(Robot robot){		//Csak a szkeletonhoz kell
		activePlayer=robot;
	}
	public void addAlivePlayer(Robot robot){		//csak a szkeletonhoz kell
		alivePlayers.add(robot);
	}
	public void setMap(Map map){					//csak a szkeletonhoz kell
		this.map=map;
	}
	
	
	

}