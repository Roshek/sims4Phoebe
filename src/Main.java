import java.security.AllPermission;
import java.util.Random;
import java.util.Scanner;


public class Main {
	
	/**Men� ki�r�sa
	 * Ki�rja a men�pontokat
	 */
	public static void printMenu(){
		System.out.println("Sims4 team - Phoebe szkeleton");
		System.out.println("\nMenu:");
		System.out.println("1. J�t�k ind�t�sa\n" +
				"2. Trap elhelyezese\n" +
				"3. L�p�s �tad�sa\n" +
				"4. K�r �tad�sa\n" +
				"5. Kil�p�s tesztel�se\n" +
				"6. Kil�p�s a Szkeleton tesztb�l");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		printMenu();
		Scanner scanchoice = new Scanner(System.in);
		int chosenMenu;

		do {
			System.out.println("Adja meg a parancs k�dj�t:");
			if(scanchoice.hasNextInt())
	            chosenMenu = scanchoice.nextInt();
			else { chosenMenu = 6;}
		    switch (chosenMenu)
		    {
		    	/** Men� 1: J�t�k ind�t�sa
		    	 * A j�t�koszsz�m megad�sa ut�n a j�t�khoz sz�ks�ges objektumok
		    	 * l�trehoz�sa �s be�ll�t�s�nak tesztel�se
		    	 */
		        case 1:
		        	System.out.println("- 1. J�t�k ind�t�sa");
		        	System.out.println("? H�ny j�t�kos van? (1-4)");
		        	int num;
		        	Scanner num_in = new Scanner(System.in);
		        	if(num_in.hasNextInt())
			            num = num_in.nextInt();
					else { num = 1;}
		            Startup startup = new Startup();
		            startup.setPlayers_num(num);
		            startup.run();
		            break;
		            
		        /** Men� 2: Trap elhelyez�se
		         * A kiv�lasztott csapda elhelyez�s�nek tesztel�se
		         */
		        case 2:
		        	System.out.println("- 2. Trap elhelyez�se");
		        	System.out.println("? 2.1  [O]lajat vagy [R]agacsot hagyjon a robot? (O/R)");
		        	Scanner in = new Scanner(System.in);
		        	String be = null;
					do{
			        	be=in.nextLine();		        		
			        	}while(!be.equals("O") && !be.equals("R"));
			        if(be.equals("O")){
			        	System.out.println("Sz�ks�ges objektumok l�trehoz�sa:");
			        	Engine engine_tmp = new Engine();
			        	Coord pos= new Coord(0,0);
			        	Robot robot_tmp = new Robot(engine_tmp);
			        	
			        	System.out.println("\n Tesztel�s:");
			        	robot_tmp.placeOil();
			        	
			        	
			        }else if(be.equals("R")){
			        	System.out.println("Sz�ks�ges objektumok l�trehoz�sa:");
			        	Engine engine_tmp = new Engine();
			        	Coord pos= new Coord(0,0);
			        	Robot robot_tmp = new Robot(engine_tmp);
			        	
			        	System.out.println("\n Tesztel�s:");
			        	robot_tmp.placeSlime();
			        }
		            break;
		            
		        /** Men� 3: L�p�s �tad�s�nak tesztel�se
		         * Egy robot befejezi a l�p�s�t
		         */
		        case 3:
		        	System.out.println("- 3. L�p�s �tad�sa");
		        	/**Sz�ks�ges objektumok l�trehoz�sa
		        	 * Engine, Robot �s Coord ideiglenes objektumok
		        	 * A robot be�ll�t�sa akt�v j�t�kosnak az enginben.
		        	 */
		        	System.out.println("Teszthez sz�ks�ges objektumok l�trehoz�sa:");
		        	
		        	Engine engine_tmp_3 = new Engine();
		        	Robot robot_tmp_3 = new Robot(engine_tmp_3);
		        	Coord coord_tmp_3 = new Coord(0,0);
		        	engine_tmp_3.setActivePlayer(robot_tmp_3);
		        	
		        	/**Tesztel�s
		        	 * az engine turnPassed() met�dus�nak futtat�sa
		        	 */
		        	System.out.println("\n Tesztel�s:");
		        	engine_tmp_3.turnPassed(coord_tmp_3);
		        	
		            break;
		        /** Men� 4: K�r v�ge
		         * Vizsg�lat, hogy a robot hol �ll, majd annak megfelel�en a robot l�ptet�se.
		         */
		        case 4:
		        	System.out.println("- 4. Robot mozgat�sa");
		        	
		        	/** Sz�ks�ges objektumok l�trehoz�sa
		        	 * Engine, Map, Robot, Oil �s Slime oszt�lyok ideiglenes p�ld�nyos�t�sa,
		        	 * robot hozz�ad�sa az engine alivePlayers list�j�hoz,
		        	 * map be�ll�t�sa az engine.map-nek
		        	 * Olaj az (1,0), ragacs a (2,0) poz�ci�ba ker�l, �gy a tesztel�t 
		        	 * a robot hely�nek �ll�t�s�val lehet szab�lyozni
		        	 */
		        	System.out.println("Sz�ks�ges objektumok l�trehoz�sa:");
		        	Engine engine_tmp_4 = new Engine();
		        	Robot robot_tmp_4 = new Robot(engine_tmp_4);
		        	Oil oil_tmp_4 = new Oil();
		        	oil_tmp_4.setPos(new Coord(1,0));
		        	Slime slime_tmp_4 = new Slime();
		        	slime_tmp_4.setPos(new Coord(2,0));
		        	engine_tmp_4.addTrap(oil_tmp_4);
		        	engine_tmp_4.addTrap(slime_tmp_4);
		        	engine_tmp_4.addAlivePlayer(robot_tmp_4);
		        	Map map_tmp_4=new Map();
		        	engine_tmp_4.setMap(map_tmp_4);
		        	
		        	/**A tesztel�s esetei
		        	 * A robotok hely�nek megad�s�val tesztelhet�ek, hogy milyen mez�r�l l�pnek el azok.
		        	 */
		        	
		        	System.out.println("\n Tesztel�s:");
		        	
		        	System.out.println("? 4.1 A robot [O]lajon, [R]agacson vagy [U]res mez�n �ll? (O/R/U)");
		        	Scanner in_4 = new Scanner(System.in);
		        	String be_4 = null;
					do{
			        	be_4=in_4.nextLine();		        		
			        	}while(!be_4.equals("O") && !be_4.equals("R") && !be_4.equals("U"));
			        if(be_4.equals("O")){
			        	robot_tmp_4.setPosition(new Coord(1,0));
			        	engine_tmp_4.roundOver();
			        }else if(be_4.equals("R")){
			        	robot_tmp_4.setPosition(new Coord(2,0));
			        	engine_tmp_4.roundOver();
			        }else if(be_4.equals("U")){
			        	robot_tmp_4.setPosition(new Coord(3,0));
			        	engine_tmp_4.roundOver();
			        }
		        	break;
		        	
		       	/** Menu 5: Kil�p�s
		       	 * A h�romf�le kil�p�si felt�tel tesztel�se
		         */      	
		        case 5: 
		        	System.out.println("- 5. Kil�p�s");
		        	/** Sz�ks�ges objektumok l�trehoz�sa
		        	 * 
		        	 */	        	
		        	System.out.println("Sz�ks�ges objektumok l�trehoz�sa:");
		        	
		        	Engine engine= new Engine();
		        	
		        	Random rudi=new Random();
		        	
		        	Robot a=new Robot(engine);
		        	a.setRoad(rudi.nextDouble()*100);
		        	Robot b=new Robot(engine);
		        	b.setRoad(rudi.nextDouble()*100);
		        	Robot c=new Robot(engine);
		        	c.setRoad(rudi.nextDouble()*100);
		        	Robot d=new Robot(engine);
		        	d.setRoad(rudi.nextDouble()*100);
		        	
		        	//todo
		        	System.out.println("\n Tesztel�s:");		        	
		        	System.out.println("? 5.1  Mindenki [L]eesett, [E]lfogytak a k�r�k, vagy [K]il�p�s a programb�l? (L/E/K)");
		        	Scanner in_5 = new Scanner(System.in);
		        	String be_5 = null;
					do{
			        	be_5=in_5.nextLine();		        		
			        	}while(!be_5.equals("L") && !be_5.equals("E") && !be_5.equals("K"));
		        	if(be_5.equals("L")){
		        		engine.allPlayersDead();
		        	}else if(be_5.equals("E")){
		        		engine.whoWins();
		        		engine.quit();
		        	}else if(be_5.equals("K")){
		        		engine.quit();
		        	}
		            break;
		            
		        /** Men� 6: Kil�p�s a szkeletonb�l
		        * Bez�rul a program.
		        */
		        case 6: 
		        	System.out.println("Sikeres kil�p�s.");
		            break;
		        default:
		            System.out.println("�rv�nytelen bevitel.");
		    }   
		} while (chosenMenu != 6);
	}

}
