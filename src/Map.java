import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map {

	private BufferedImage field;
	
	private ArrayList<Coord> midline = new ArrayList<Coord>();

	/**\brief Betolti a kapott palyat
	 * 
	 * A kapott string alapjan megnyitja
	 * es eltarolja a kepet.
	 * 
	 * @param s
	 */

	
	public Map(){
	    try {
	    	field = ImageIO.read(new File("map.png"));
	    } 
	    catch (IOException e) {
	     
	    }
		calculateMidline();
	}
	
	
	public void load(){
		field=Resources.getMap();
	}


	/**\brief Megnezi leesett-e a robot
	 * 
	 * Megnezi a robot jelenlegi helyet,
	 * es ha az a palyan a szakadek szine,
	 * akkor visszater true-val, jelezve,
	 * hogy a robot a melybe zuhant.
	 *
	 * @param c
	 */
	public Boolean fall(Coord c) {						//FIXED
		
		if(c.getX()>field.getWidth() || c.getY()>field.getHeight() || c.getX() < 0 || c.getY() < 0)
			return true;
		
		System.out.println("(3,3) pont színe: " + field.getRGB(3, 3) + " robot helyének színe: " + field.getRGB(c.getX(), c.getY()));
		
		if(field.getRGB(c.getX(), c.getY()) == field.getRGB(0, 0)){
			System.out.println("Fentmaradt");
			return false;
		}else{
			System.out.println("Leesett");
			return true;
		}
	}

	/**\brief Megadja a jatekosok kezdohelyeit
	 * 
	 * A jatekosok szamatol fuggoen
	 * visszaad egy egyenlo tavolsagra levo pontokbol
	 * allo listat
	 * 
	 * @param numberOfPlayers
	 */
	public ArrayList<Coord> putPlayers(int numberOfPlayers, int offset) {		
		
		ArrayList<Coord> tmp=new ArrayList<Coord>();
		for(int i=0;i<numberOfPlayers;i++){
			tmp.add(getSpawntPoint(numberOfPlayers, i, offset));
		}
		return tmp;
	}

	public BufferedImage getField() {					
		
		return field;
	}

	/**
	 * Beallitja a fieldet.
	 * 
	 * @param field
	 */
	public void setField(BufferedImage field) {			
		//System.out.println("->[:Map].setField()");
		
		this.field=field;
	}

	
	/**\Brief Kiszamolja a palya kozepvonalat, amin a minirobotok haladnak, illetve amik kozul kikerulnek a spawnpointok.
	 * Az algoritmus kiszamolja (~nagyjabol) a palya kozepvonalat, 
	 * amit ~20 tavolsagra levo pontok halmazaval reprezental
	 * 
	 * Az algoritmus lenyege, hogy a palya szelen egy pontbol kiindulva a szemkozti oldal fele
	 * behuz egy rakat egyenest par fok kulonbseggel, majd ha mar eleg sok indul ki ugyanabbol a pontbol
	 * akkor a masik oldalrol is huz egy rakat egyenest az elsore
	 * es igy oda-vissza korbemegy az egesz palyan
	 * Az igy letrejott vonalaknak aztan veszi a kozeppontjat, kicsit emgritkitja hogy ne legyen tul sok
	 * es maris kaptunk egy kozelitest a palya kozepvonalara
	 * 
	 * BEWARE: Heavy math incoming
	 * 
	 */

	private void calculateMidline(){
		int voidColor = field.getRGB(field.getMinX()+1, field.getMinY());
		ArrayList<Coord> sideline = new ArrayList<Coord>();
		ArrayList<Coord> allmidline = new ArrayList<Coord>();
		ArrayList<Coord> cornerstones = new ArrayList<Coord>();
		
		/*MAGIC NUMBERS*/
		//ne kerdezd
		double dA = 4; 
		double dRB = 50;
		double dR = 5;
		double dSP = 20;
		double dSPB = 240;
		double maxSP = 14;
		double invertA = 190;
		double maxK = 70;
		int miniRobotStepD = 20;
		/*END OF MAGIC NUMBERS*/
		
		
		Coord A = new Coord(76,55);
		Coord B = new Coord(182,176);
		sideline.add(A);
		cornerstones.add(B);
		
		
		double a = 224;
		for (int k = 0; k < maxK; k++) {
			
			double Bb = Coord.distance(A, B) * 0.95;
			
			double bX = (B.getX() - A.getX()) * 0.95 + A.getX();
			double bY = (B.getY() - A.getY()) * 0.95 + A.getY();
			
			double x=0,y=0;
			Coord prev = new Coord((int)x,(int)y);
			Coord tst = new Coord((int)x,(int)y);
			int i=0;
			do{
				
				double tX = bX + (Math.cos(Math.toRadians((a))) * Bb);
				double tY = bY + (Math.sin(Math.toRadians((a))) * Bb);
				
				if (k%2==0) a-=dA;
				else a+=dA;
				
				
				double d = Coord.distance(new Coord((int)tX,(int)tY), B);
				
				x = tX;
				y = tY;
				
				int j = 0;
				do
				{
					double ratio = (dRB + j) / d; 
				
					x = tX - B.getX(); 
					y = tY - B.getY();
				
					x = x * ratio + B.getX();
					y = y * ratio + B.getY();
					
					j += dR;
				}
				while (field.getRGB((int) x, (int) y) != voidColor);	
				prev = new Coord(tst);
				tst = new Coord((int)x,(int)y);
				
				if (i>1 && Coord.distance(prev, tst) < dSP && Coord.distance(tst, B) < dSPB){
					sideline.add(new Coord(tst));
					allmidline.add(new Coord(  ((B.getX()-tst.getX())/2 + tst.getX()), ((B.getY()-tst.getY())/2 + tst.getY())) );
				}
				if (i>1 && (Coord.distance(prev, tst) > dSP || Coord.distance(tst, B) > dSPB)){
					break;
				}
					
				if (i<=1 && Coord.distance(tst, B) < dSPB) {
					sideline.add(new Coord(tst));
					allmidline.add(new Coord(  ((B.getX()-tst.getX())/2 + tst.getX()), ((B.getY()-tst.getY())/2 + tst.getY())) );
				}
				
				
				prev = new Coord(tst);
				
				i++;
			}while (i != maxSP);

			
			A = new Coord(B);
			B = new Coord(prev);
			
			sideline.add(null);
			cornerstones.add(new Coord(B));
			
			if (k%2==0)a += invertA;
			else a -= invertA;
			

		}
		
		Coord ls = allmidline.get(0);
		midline.add(ls);
		for (Coord c : allmidline) {
			if (Coord.distance(ls, c) > miniRobotStepD) {
				midline.add(c);
				ls = c;
			}
		}
	}
	

	/** Visszaadja a legkozelebbi kozepvonalon levo pontot
	 * 
	 * @param c - Amihez viszonyitunk
	 * @return A pont ami a legkozelebb van
	 */
	public Coord getClosesMidpoint(Coord c){
		int i = 0;
		double d = 1000;
		for (Coord cr : midline) {
			if (Coord.distance(cr, c) < d){
				i = midline.indexOf(cr); 	
				d = Coord.distance(cr, c);
			}
		}
		return midline.get(i);
	}

	/** Megad egy spawnpointot
	 * 
	 * @param all - Ennyi spawnpoint van osszesen
	 * @param nth - Ennyiedik spawnpoint kell nekünk
	 * @param offset - El lehet tolni hogy ne ugyanott spawnoljon a Robot es a minirobot
	 * @return - A Spawnpoint helye
	 */
	
	private Coord getSpawntPoint(int all, int nth, int offset){
		return midline.get((midline.size() / all) * nth + offset);
	}
	
	
	/** Minirobot mozgasahoz donti el hogy a midpointokon milyen iranyba induljon el
	 * 
	 * @param robot - A miniribot
	 * @param trapPos - A csapda ami fele a minirobot akar menni
	 * @return - True ha a midpointokon az indexeket novelve kell haladnia, false ha csokkentve
	 */
	public boolean getMoveDir(Coord robot, Coord trapPos){
		int robotPos = midline.indexOf(robot);
		int mSize = midline.size();
		
		int nextPos = robotPos + 1;
		if (nextPos == mSize) nextPos = 0;
		
		int prevPos = robotPos - 1;
		if (prevPos == -1) prevPos = mSize - 1;
		
		System.out.println("");
		System.out.println("_______-----------");
		
		System.out.println("MiniRobotPos: " + robot.getX() + " : " + robot.getY()  + "   robotPos: " + robotPos + "   mSize: " + mSize + "   nextPos: " + nextPos + "   prevPos: " + prevPos);
		
		System.out.println("");
		System.out.println("");
		if (Coord.distance(midline.get(nextPos), trapPos) < Coord.distance(midline.get(prevPos), trapPos)) return true;
		else return false;
	}
	
	
	/**
	 * A haladasi iranynak megfelelo kovetkezo midpointot adja vissza
	 * @param c - Az aktualis hely ahol vagyunk, ami szinten egy midpoint
	 * @param dir - Az irany
	 * @return - A kovetkezpo midpoint
	 */
	public Coord getNextMidpoint(Coord c, boolean dir) {
		int nextPos = 0;
		if (dir) nextPos = midline.indexOf(c) + 1;
		if (nextPos == midline.size()) nextPos = 0;
		if (!dir) nextPos = midline.indexOf(c) - 1;
		if (nextPos == -1) nextPos = midline.size() - 1;
		
		return midline.get(nextPos);
		
	}

	public ArrayList<Coord> getMidline() {
		return midline;
	}

	public void setMidline(ArrayList<Coord> midline) {
		this.midline = midline;
	}

}
