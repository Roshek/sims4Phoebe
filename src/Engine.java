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
	
	
	public MainWindow getWindow() {
		return window;
	}

	public void setWindow(MainWindow window) {
		this.window = window;
		view.setWindow(window);
	}

	public Arrow getArrow(){
		return arrow;
	}
	
	public Robot getActivePlayer(){
		return activePlayer;
	}
	

	public Controller getController(){
		return view.getController();
	}
	
	
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
				if(Coord.distance(pos,tmp.getPos()) > Coord.distance(pos, x.getPos())){
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
	 * Minden meg elo robotra megh�vja a
	 * calCulateCoords() fuggvenyt, es 
	 * ellenorzi, hogy leesett-e valamelyik.
	 * Ha igen, akkor azt atteszi a halott 
	 * robotok koze.
	 */
	
	private void moveRobots(){					//KeSZ
		
		//int numberOfRobotsAtStartOfCycle=alivePlayers.size();
		
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
	 * a robotot, hogy az beall�tsa a 
	 * neki megfelelo modos�tasokat.
	 * 
	 */
	
	private void trapRobots(){					//KeSZ				//MoDOSULT
		
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
		
	}
	
	/**\brief Kor vege
	 * 
	 * Amikor az utolso jatekos is elpasszolta
	 * a koret, akkor h�vodik meg. Megh�vja a
	 * az engine trapRobots() es moveRobots()
	 * fuggvenyeit ilyen sorrendben.
	 * 
	 */
	
	public void roundOver(){					//KeSZ		//csak a szkeletonhoz public
		
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
		
		
		releaseMiniRobots();
		
		round_num--;
		
		if(alivePlayers.size()==1 || round_num==0){
			whoWins();
			endGame();
		}
		
		
		if(alivePlayers.size()==0)
			endGame();
		
	}
	
	private void endGame() {
				whoWins(); //beallitjuk hogy ki nyert
		JFrame endframe = new JFrame();
		endframe.setTitle("Jatek vege");
		endframe.setSize(300, 100);
		endframe.setBackground(Color.RED);
		endframe.setResizable(false);
		endframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
		endframe.setLocationRelativeTo(null);
		
		JPanel endpanel = new JPanel();
		JLabel endlabel = new JLabel("A leghoszabb utat a(z) "+winner.getID()+". robot tette meg.");
		JButton endbutton = new JButton("Folytatas");
		
		endframe.add(endpanel);
		endpanel.add(endlabel);
		
		endbutton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						sm = new StartUpMenu();
						window.dispose();
						endframe.dispose();
						
					}});
		endpanel.add(endbutton);	
		endframe.setVisible(true);
	}

	private void releaseMiniRobots() {
		if(miniRobots.size()<=1 /*&& round_num!=30*/){
			
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
	
	public Engine(){							//KeSZ
		
		alivePlayers=new ArrayList<Robot>();
		deadPlayers=new ArrayList<Robot>();
		miniRobots=new ArrayList<MiniRobot>();
		traps=new ArrayList<Trap>();
		map=new Map();
		
		player_num=0;
		round_num=30;
		

		
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
	public void init(int numberOfPlayers) {			//KeSZ
		
		Resources.load();
		arrow=new Arrow();
		
		map.load();
		
		view = new View(this);
		view.setWindow(window);
		
		//nyil es annak grafikus parjanak peldanyositasa
		arrow=new Arrow();
		GraphicArrow gArrow = new GraphicArrow(arrow);
		view.setGArrow(gArrow);
		
		for(int i=0;i<numberOfPlayers;i++){
			Robot tmp=new Robot(this);
			alivePlayers.add(tmp);
			tmp.setID(RobotID);
			RobotID++;
		}
		
		ArrayList<Coord> tmp=map.putPlayers(numberOfPlayers,0);
		
		for(int i=0;i<numberOfPlayers;i++){
			alivePlayers.get(i).setPosition(tmp.get(i));		//nem valid amig nincs putPlayers
		}
		activePlayer=alivePlayers.get(0);
		arrow.setStartPoint(activePlayer.getPosition());
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
	public void turnPassed() {		//TO BE REVIEWED				//MODIFIED
		
		for(Robot r : alivePlayers)
			System.out.println("valtas elott"+r.getModifier().getX()+","+r.getModifier().getY());
		activePlayer.setModifier(arrow.getModifier());		//A kort atado jatekos megkapja a beallitott nyil modifieret
		for(Robot r : alivePlayers)
			System.out.println(r.getModifier().getX()+","+r.getModifier().getY());

		
//		int index=alivePlayers.indexOf(activePlayer);
//		Robot newActivePlayer=alivePlayers.get((index+1)%alivePlayers.size());		//O.o
//		
//		activePlayer=newActivePlayer;
//		
//		//activePlayer=alivePlayers.get((alivePlayers.indexOf(activePlayer)+1)%alivePlayers.size());
		
		int index=alivePlayers.indexOf(activePlayer);
		//System.out.println("Aki atadta a kort: "+ index);
		if(index==alivePlayers.size()-1){
			roundOver();
			activePlayer=alivePlayers.get(0);
			arrow.setStartPoint(activePlayer.getPosition());
			for(Robot r : alivePlayers)
				System.out.println(r.getModifier().getX()+","+r.getModifier().getY());
			return;
		}
		index++;
		activePlayer=alivePlayers.get(index);
		arrow.setStartPoint(activePlayer.getPosition());
		for(Robot r : alivePlayers)
			System.out.println(r.getModifier().getX()+","+r.getModifier().getY());
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
	public void dieRobot(Robot r) {					//KeSZ
		r.setAlive(false);
	}

	/**\brief Megnezi ki nyert
	 * 
	 * Vegignezi a robotok road attributumat
	 * es kivalasztja a legnagyobbat.
	 * Ezt eltarolja a winner attributumban.
	 *  
	 */
	
	private void whoWins() {							//KeSZ
		//TODO
		Robot winningPlayer;
		if(!alivePlayers.isEmpty())
			winningPlayer=alivePlayers.get(0);
		else winningPlayer=deadPlayers.get(0);
		for(Robot tmp: alivePlayers){
			if(tmp.getRoad()>winningPlayer.getRoad()){ winningPlayer=tmp;}
		}
		for(Robot tmp: deadPlayers){
			if(tmp.getRoad()>winningPlayer.getRoad()){ winningPlayer=tmp;}
		}
		setWinner(winningPlayer);
	}

	public int getPlayer_num() {					//KeSZ
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
