import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Engine {

	
	// Valtozok inicializalasa
	private static final int EXIT_ON_CLOSE = 0;
	StartUpMenu sm;
	private int player_num;
	private int round_num;
	public ArrayList<Robot> alivePlayers;
	public ArrayList<Robot> deadPlayers;
	public ArrayList<Trap> traps;
	
	public ArrayList<MiniRobot> miniRobots;
	
	public Map map;

	public Robot activePlayer;
	private Robot winner;
	
	private MainWindow window;
	private Arrow arrow;

	private int RobotID=1;
	
	public View view;

	
	/** Window getter
	 * 
	 * @return
	 */
	public MainWindow getWindow() {
		return window;
	}

	/** Window setter
	 * 
	 * @param window
	 */
	public void setWindow(MainWindow window) {
		this.window = window;
		view.setWindow(window);
	}

	/** Arrow getter
	 * 
	 * @return
	 */
	public Arrow getArrow(){
		return arrow;
	}
	
	/** activePlayer getter
	 * 
	 * @return
	 */
	public Robot getActivePlayer(){
		return activePlayer;
	}
	
	/** Controller getter
	 * 
	 * @return
	 */
	public Controller getController(){
		return view.getController();
	}
	
	/** Minirobotok mozgatasa
	 * Vegigmegy a minirobotok listajan,
	 * Ellenorzi hogy az uj poziciojukban leestek-e
	 */
	private void moveminiRobots(){				
		for(MiniRobot x : miniRobots){
			x.move();
			Coord place=x.getPosition();
			boolean dead=map.fall(place);
			if(dead){
				x.setAlive(false);
			}
		}
	}
	
	/** Legkozelebbi csapda megadasa
	 *  A parameterkent kapott poziciohoz legkozelebbi
	 *  csapda koordinatajat adja vissza
	 * 
	 * @param pos
	 * @return
	 */
	public Coord getClosestTrap(Coord pos){		
		if(!traps.isEmpty()){																// Ha van csapda a palyan
			Trap tmp=traps.get(0);
			for(Trap x: traps){																// vegigmegy a csapdakon,
				if(Coord.distance(pos,tmp.getPos()) > Coord.distance(pos, x.getPos())){		// majd kivalasztja a legkozelebbit
					tmp=x;
				}
			}
			return tmp.getPos();															// es visszaadja.
		}
		return null;																		// Ha nincs csapda a palyan, null-al ter vissza
	}
	
	/** Minirobotok tesztelese a palyan
	 * Tesztek, hogy a minirobotok utkoztek-e
	 * valamivel a palyan
	 * 
	 */
	private void testMiniRobotForTraps(){
		for(MiniRobot x: miniRobots){							// Vegig kell menni minden miniRoboton,
			Coord place=x.getPosition();						// elmenteni az aktualis poziciot
			
			for(Trap i: traps){									// Teszt az osszes csapdara, hogy ralepett-e
				if(i.collide(place)){							// az aktualisan vizsgalt miniRobot
					i.steppedOnByMiniRobot(x);
				}
			}
			for(Robot i: alivePlayers){							// Hasonloan az elozohoz, nagy robotra tesztelve
				if(i.collide(place))
					i.steppedOnByMiniRobot(x);
			}
			for(MiniRobot i: miniRobots){
				if(i.collide(place) && (i.getID()!=x.getID()))  // Vegigmegy egy minirobot az osszes miniroboton, ezert ki kell zarni, hogy magat is vizsgalja
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
		
		System.out.println("\nMinenki leesett, nincs gyoztes.\n");
		endGame();
		}
	
	
	/**\brief Kilepes
	 * 
	 * Bezarja a programot.
	 * 
	 */
	public void quit(){
		System.exit(0);
	}
	
	
	/**\brief Robotok mozgatasa
	 * 
	 * Minden meg elo robotra meghivja a
	 * calCulateCoords() fuggvenyt, es 
	 * ellenorzi, hogy leesett-e valamelyik.
	 * Ha igen, akkor azt atteszi a halott 
	 * robotok koze.
	 */
	private void moveRobots(){					
		
		for(Robot tmp: alivePlayers){
			System.out.print(tmp.getPosition().getX() + " " + tmp.getPosition().getY() + " ugras elotti koord, ugras utani: ");
			tmp.calculateCoords();
			Coord newpos=tmp.getPosition();
			System.out.println(newpos.getX() + " " + newpos.getY() + " x, y koordja a mozgatott robotnak, id: " + tmp.getID());
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
	 * a robotot, hogy az beallitsa a 
	 * neki megfelelo modositasokat.
	 * 
	 */
	private void trapRobots(){					
		
		for(Robot robot: alivePlayers){
			Coord pos=robot.getPosition();
			
			for(Trap trap: traps){
				if(trap.getOwner() != robot && trap.collide(pos)){
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
		
	}
	
	/**\brief Kor vege
	 * 
	 * Amikor az utolso jatekos is elpasszolta
	 * a koret, akkor hivodik meg. Meghivja a
	 * az engine trapRobots() es moveRobots()
	 * fuggvenyeit ilyen sorrendben.
	 * 
	 */
	
	private void roundOver(){					
		
		trapRobots();
		moveRobots();
		
		moveminiRobots();
		testMiniRobotForTraps();
			
		/* ===== TAKARITAS BEGINS =====
		 * 		Vegigmegy a palya elemeinek listain,
		 * 		ha talal mar elevult elemet, torli azt.
		 * 		Listak sorrendben: 	alivePlayers
		 * 							traps
		 * 							miniRobots
		 */
		
		Iterator<Robot> it=alivePlayers.iterator();				
		while(it.hasNext()){
			Robot x=it.next();
			if(!x.isAlive()){
				System.out.println("I SHAMELESSLY RAPED A FELLOW ROBOT :( ITT: (" + x.getPosition().getX() + "," + x.getPosition().getY()+")");
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
				System.out.println("Koordinata: ("+x.getPosition().getX() + "," + x.getPosition().getY()+")");
				it3.remove();
			}
		}
		/*
		 * ===== TAKARITAS ENDS =====
		 */
		
		
		for(Trap i:traps){																			// Csapdak oregitese
			i.timePassed();
		}
		
		
		releaseMiniRobots();
		
		round_num--;																				// Hatralevo korok szamanak csokkentese
		
		if(alivePlayers.size()==1 || round_num==0){													// Ellenorzes nyertesekre
			whoWins();
			endGame();
		}
		
		
		if(alivePlayers.size()==0)																	// Jatek vege, ha mindenki meghalt
			endGame();
		
	}
	
	/** A postgame megvalositasa
	 *  A jetek vegeztevel ablak kozli az eredmenyt
	 */
	private void endGame() {
				whoWins(); 																			// Beallitjuk hogy ki nyert a winner valtozoba
				
		/*
		 * Felugro ablak beallitasai
		 */
		final JFrame endframe = new JFrame();
		endframe.setTitle("Jatek vege");
		endframe.setSize(500, 100);
		endframe.setBackground(Color.RED);
		endframe.setResizable(false);
		endframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		endframe.setLocationRelativeTo(null);
		
		JPanel endpanel = new JPanel();
		JLabel endlabel = new JLabel("A leghoszabb utat a(z) "+winner.getID()+". robot tette meg az eletben maradtak kozul.");
		JButton endbutton = new JButton("Folytatas");
		
		endframe.add(endpanel);
		endpanel.add(endlabel);
		
		// Az ablak gombjanak esemenye
		endbutton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						sm = new StartUpMenu();														// A folytatasra kattintva a kezdomenube kerulunk.
						window.dispose();
						endframe.dispose();
						
					}});
		endpanel.add(endbutton);	
		endframe.setVisible(true);
	}

	/** miniRobotok palyara rakasa
	 * Ot koronkent minirobotok elednek a palya egyes pontjain
	 */
	private void releaseMiniRobots() {
		if(miniRobots.size()<=1 && round_num!=30){
			
			ArrayList<Coord> spawnPoses = map.putPlayers(3 - miniRobots.size(), 15); 
			for(int i=0;i< 3 - miniRobots.size();i++){
				MiniRobot tmp = new MiniRobot(this);
				miniRobots.add(tmp);
				tmp.setPosition(spawnPoses.get(i));
				
			}
		}
		
	}

	//PUBLIC, TERVEZETT FuGGVeNYEK
	
	/**\brief Engine konstruktor
	 * 
	 * Inicializalja az ArrayList-eket es
	 * beallitja a max korok szamat. 
	 */
	
	public Engine(){							
		
		alivePlayers=new ArrayList<Robot>();
		deadPlayers=new ArrayList<Robot>();
		miniRobots=new ArrayList<MiniRobot>();
		traps=new ArrayList<Trap>();
		map=new Map();
		
		player_num=0;
		round_num=30;										// Korok szama: 30
		

		
	}
	
	/**\brief A fo playfuggveny, itt fut a jatek nagy resze
	 * 
	 * Am�g a korszamlalo el nem eri a nullat
	 * egyesevel vegigmegy az elo robotokon,
	 * majd var, am�g a kezelofelulet felebreszti
	 * a kor atpasszolasaval. Amikor vegig ert a 
	 * a robotokon megh�vja a roundOver() fuggvenyt.
	 * Amikor elfogytak a korok megh�vja a whoWins()
	 * fuggvenyt.
	 */
	
	public void play() {						//PARTIALLY READY
		
		while(round_num>0){
			for(Robot tmp: alivePlayers){
				activePlayer=tmp;
				try {
					wait();
				} catch (InterruptedException e) {
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
	public void init(int numberOfPlayers) {			
		
		Resources.load();
		arrow=new Arrow();
		
		map.load();
		
		view = new View(this);
		view.setWindow(window);
		
		// Nyil es annak grafikus parjanak peldanyositasa
		arrow=new Arrow();
		GraphicArrow gArrow = new GraphicArrow(arrow);
		view.setGArrow(gArrow);
		
		// A beallitott szamu jatekosok letrehozasa es beallitasa
		for(int i=0;i<numberOfPlayers;i++){
			Robot tmp=new Robot(this);
			alivePlayers.add(tmp);
			tmp.setID(RobotID);
			RobotID++;
		}
		
		// tmp lista tarolja, hogy hova kell lerakni a robotokat
		ArrayList<Coord> tmp=map.putPlayers(numberOfPlayers,0);
		
		// Robotok elhelyezese a palyan
		for(int i=0;i<numberOfPlayers;i++){
			alivePlayers.get(i).setPosition(tmp.get(i));		
		}
		activePlayer=alivePlayers.get(0);					// Aktiv jatekos eloszor a legelso
		arrow.setStartPoint(activePlayer.getPosition());	// Nyil beallitasa az aktiv jatekoshoz
	}

	/**\brief Kor passzolasa
	 * 
	 * A kezelofelulettol kapott vektort
	 * atadja az epp aktiv robotnak az
	 * uj modifierekent, es felebreszti az
	 * Engine.play()-ben varakozo foszalat,
	 * hogy tovabblepjen a jatek a kovetkezo
	 * jatekosra.
	 * 
	 * @param modifier
	 */
	public void turnPassed() {	
		
		activePlayer.setModifier(arrow.getModifier());			//A kort atado jatekos megkapja a beallitott nyil modifieret

		int index=alivePlayers.indexOf(activePlayer);			// Az aktualis aktivPlayer listabeli helyenek lekerese

		if(index==alivePlayers.size()-1){
			roundOver();										// Ha az utolso jatekos is atadta a lepeset, a kornek vege
			if(alivePlayers.size()==0)return;					// Ha mindenki halott, rogton visszater
			activePlayer=alivePlayers.get(0);					// Az aktivPlayer visszaal a legelso robotra
			arrow.setStartPoint(activePlayer.getPosition());	// A nyil is igazodik
			return;
		}
		index++;												// Kovetkezo jatekosra valtas a listabol
		activePlayer=alivePlayers.get(index);
		arrow.setStartPoint(activePlayer.getPosition());
	}

	/**\brief uj csapda hozzaadasa
	 * 
	 * Eltarolja a csapdak kozott a
	 * parameterben kapott csapdat.
	 * 
	 * @param x
	 */
	public void addTrap(Trap x) {					//KeSZ
		traps.add(x);
	}

	/**\brief atrakja a robotot a halottak koze
	 * 
	 * A parameterben kapott robotot kiveszi az
	 * elok kozul es atteszi a halottak koze.
	 * A robot alive flagjat is atall�tja.
	 * 
	 * @param r
	 */
	public void dieRobot(Robot r) {					
		r.setAlive(false);
	}

	/**\brief Megnezi ki nyert
	 * 
	 * Vegignezi a robotok road attributumat
	 * es kivalasztja a legnagyobbat.
	 * Ezt beallitja a winner attributumban.
	 *  
	 */
	
	private void whoWins() {							
		Robot winningPlayer;
		if(!alivePlayers.isEmpty())
			winningPlayer=alivePlayers.get(0);
		else winningPlayer=deadPlayers.get(0);
		for(Robot tmp: alivePlayers){
			if(tmp.getRoad()>winningPlayer.getRoad()){ winningPlayer=tmp;}
		}
		/*for(Robot tmp: deadPlayers){
			if(tmp.getRoad()>winningPlayer.getRoad()){ winningPlayer=tmp;}
		}*/
		setWinner(winningPlayer);
	}

	/* ===== Getter-setter metodusok =====
	 * 
	 */
	
	public int getPlayer_num() {					
		return this.player_num;
	}


	public void setPlayer_num(int player_num) {		//KeSZ
		this.player_num = player_num;
	}

	public int getRound_num() {						//KeSZ
		return this.round_num;
	}

	
	public void setRound_num(int round_num) {		//KeSZ
		this.round_num = round_num;
	}
	
	

	public ArrayList<Trap> getTraps() {				//KeSZ
		return this.traps;
	}

	
	public void setTraps(ArrayList<Trap> traps) {	//KeSZ
		this.traps = traps;
	}
	
	
	public Map getMap() {
		return map;
	}

	public Robot getWinner() {
		return winner;
	}

	public void setWinner(Robot winner) {
		this.winner = winner;
	}
	
	
	public void setActivePlayer(Robot robot){
		activePlayer=robot;
	}
	public void addAlivePlayer(Robot robot){
		alivePlayers.add(robot);
	}
	public void setMap(Map map){
		this.map=map;
	}

}
