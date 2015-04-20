import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main2 {

	private static boolean stop = false;			//exitProto parancsra v�lt, meg�ll�tja a beolvas�ciklust
	public static Map map;
	public static Engine engine;
	
	//korl�tozott sz�m� robotok hozhat�ak l�tre a putRobot paranccsal.
	public static Robot robot1;
	public static Robot robot2;
	public static Robot robot3;
	public static Robot robot4;
	public static int robot_szam = 0;			//ebben a v�ltoz�ban van nyilv�ntartva, hogy mennyi robot van m�r l�trehozva
	
	//a robotokhoz hasonl�an a minirobotok is kol�tozottak.
	public static Robot miniRobot1;
	public static Robot miniRobot2;
	public static Robot miniRobot3;
	public static Robot miniRobot4;
	public static int mini_robot_szam = 0;
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		
		/** Ellenorzes az elso argumentum szerint
		 * Az elso argumentum 0 eseten a Realtime tesztel�st hivja meg,
		 * 1 eseten a 2. argumentumban megjelolt filet olvassa es annak parancsai szerint vegzi a tesztelest.
		 * ha egyik sem, a program kilep.
		 */
		if(args[0].equals(new String("0"))){							//Real time teszteset		
			System.out.println("Real-time teszt...");
			
			
			Scanner parancsRead = new Scanner(System.in);				//valtozok inicializalasa
			String parancs_arg;											//parancsok argumentumokkal
			do {														//A ciklus addig olvassa a parancsokat, amig az 'exitProto' parancs be nem erkezik
				System.out.println("Adja meg a parancs kodjat:");
				if(parancsRead.hasNext())
		            parancs_arg = parancsRead.next();						//Console olvasasa
				else { parancs_arg = new String("exitProto");}
				String[] parancs = parancs_arg.split(" ");				//A beolvasott parancs t�rdel�se sz�k�z�k szerint.
				vegrehajt(parancs);										//a parancs feldolgozasa
				
				
			}while (!stop);
			
			
		}
		else if(args[0].equals(new String("1"))){						//Filebol olvasasos teszteset
			System.out.println("File teszt...");
			
			BufferedReader br = new BufferedReader(new FileReader(args[1]));	//a m�sodik argumentum, mint f�ljn�v olvas�sa
		    try {
		        
		        String line = br.readLine();
		        while(line!= null){										//ciklus, am�g van sor a f�jlban.
		        	
		        	String[] parancs = line.split(" ");
		        	vegrehajt(parancs);
		        	line = br.readLine();
		        }
		    } finally {
		        br.close();
		    }
			
			
		}
		else
			System.out.println("Hibas argumentumok!");					//Hibas elso argumentumnal kilep a program.
	}

	/**A parancsok feldolgozasaert felelos metodus
	 * A parameterkent kapott parancs szerinte meghivja a megfelelo utasitasokat.
	 * @param parancs
	 */
	private static void vegrehajt(String[] parancs) {
		// TODO Auto-generated method stub
		System.out.println("Parancs jott.");
		
		/* V�gtelen if-else kapocs
		 * A k�l�nb�z� parancsok lekezel�se
		 */
		if(parancs[0].equals("loadMap")){
			loadMap();
		}else
		if(parancs[0].equals("putRobot")){
			putRobot(parancs[1],parancs[2]);
		}else
		if(parancs[0].equals("putMiniRobot")){
			putMiniRobot(parancs[1],parancs[2]);
		}else
		if(parancs[0].equals("putOil")){
			
		}else
		if(parancs[0].equals("putSlime")){
			
		}else
		if(parancs[0].equals("setDirection")){
			
		}else
		if(parancs[0].equals("turnOver")){
			
		}else
		if(parancs[0].equals("roundOver")){
			
		}else
		if(parancs[0].equals("listAliveMinirobot")){
			
		}else
		if(parancs[0].equals("listAliveRobots")){
			
		}else
		if(parancs[0].equals("listTraps")){
			
		}else
		if(parancs[0].equals("exitGame")){
			
		}else
		if(parancs[0].equals("changeActiveRobot")){
			
		}else
		if(parancs[0].equals("getOilNumber")){
			
		}else
		if(parancs[0].equals("getSlimeNumber")){
			
		}else
		if(parancs[0].equals("listTraps")){
			
		}else
		if(parancs[0].equals("killRobot")){
			
		}else
		if(parancs[0].equals("exitProto")){
			stop=true;
		}else
		System.out.println("Hibas bevitel");
	}
	private static void putMiniRobot(String arg1, String arg2) {
		
		switch (mini_robot_szam){
		case 0: 
			miniRobot1=new MiniRobot(engine);			//a minirobot l�trehoz�sa
			mini_robot_szam++;						//minirobotsz�m n�vel�se
			miniRobot1.setPosition(new Coord(Integer.parseInt(arg1),Integer.parseInt(arg2)));		//poz�ci� be�ll�t�sa
			break;
		case 1:
			miniRobot2=new MiniRobot(engine);
			mini_robot_szam++;
			miniRobot2.setPosition(new Coord(Integer.parseInt(arg1),Integer.parseInt(arg2)));
			break;
		case 2:
			miniRobot3=new MiniRobot(engine);
			mini_robot_szam++;
			miniRobot3.setPosition(new Coord(Integer.parseInt(arg1),Integer.parseInt(arg2)));
			break;
		case 3:
			miniRobot4=new MiniRobot(engine);
			mini_robot_szam++;
			miniRobot4.setPosition(new Coord(Integer.parseInt(arg1),Integer.parseInt(arg2)));
			break;
		default: System.out.println("Hibas parancs: ennyi miniRobot nem lehet a palyan!");	//ha t�bb robotot szeretn�nek a p�ly�n mint megengedett, hiba�zenet.
		}
		
	}

	/* A putRobot parancs megval�s�t�sa
	 * L�trehoz egy robotot, �s a param�tert be�ll�tja poz�ci�nak
	 * 
	 * @param arg1
	 * @param arg2
	 */
	private static void putRobot(String arg1, String arg2) {
		
		//Maximum 4 robotot lehet l�trehozni, a putRobot megh�v�sakor mindig a k�vetkez� statikus hely t�lt�dik fel.
		switch (robot_szam){
		case 0: 
			robot1=new Robot(engine);			//a robot l�trehoz�sa
			robot_szam++;						//robotsz�m n�vel�se
			robot1.setPosition(new Coord(Integer.parseInt(arg1),Integer.parseInt(arg2)));		//poz�ci� be�ll�t�sa
			break;
		case 1:
			robot2=new Robot(engine);
			robot_szam++;
			robot2.setPosition(new Coord(Integer.parseInt(arg1),Integer.parseInt(arg2)));
			break;
		case 2:
			robot3=new Robot(engine);
			robot_szam++;
			robot3.setPosition(new Coord(Integer.parseInt(arg1),Integer.parseInt(arg2)));
			break;
		case 3:
			robot4=new Robot(engine);
			robot_szam++;
			robot4.setPosition(new Coord(Integer.parseInt(arg1),Integer.parseInt(arg2)));
			break;
		default: System.out.println("Hibas parancs: ennyi robot nem lehet a palyan!");	//ha t�bb robotot szeretn�nek a p�ly�n mint megengedett, hiba�zenet.
		}
		
	}
	/* a loadMap parancs megval�s�t�sa
	 * 
	 * l�trehoz egy Mapet, egy Enginet, valamint bet�lti a map.png filet p�ly�nak.
	 */
	private static void loadMap() {
		map=new Map();
		engine=new Engine();
		map.load("map.png");
	}

	
}
