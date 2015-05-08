import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Map {

	private BufferedImage field;

	/**\brief Betolti a kapott palyat
	 * 
	 * A kapott string alapjan megnyitja
	 * es eltarolja a kepet.
	 * 
	 * @param s
	 */
	public void load(String s) {					
		//System.out.println("->[:Map].load()");
	
		try {
			field=ImageIO.read(new File(s));
		} catch (IOException e) {
			System.out.println("No file with that name exists");
		}
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
		
		/*if(c.getX()<=100 || c.getY()<=100)
			return true;
		return false;*/
		
		/*System.out.println("->[:Map].fall(c)");
		
		/*if(field==null)									//TESZT
			return false;	
		*/

		if(field.getRGB(c.getX(), c.getY()) == field.getRGB(0, 0)){
			return true;
		}else
			return false;		
	}

	/**\brief Megadja a jatekosok kezdohelyeit
	 * 
	 * Leteszi a megfelelo szamu jatekost,
	 *  es visszaadja a koordinataikat 
	 *  tartalmazo listat.
	 * 
	 * @param numberOfPlayers
	 */
	public ArrayList<Coord> putPlayers(int numberOfPlayers) {		
		//System.out.println("->[:Map].putPlayers(numberOfPlayers)");
		ArrayList<Coord> tmp=new ArrayList<Coord>();
		for(int i=0;i<numberOfPlayers;i++){
			tmp.add(new Coord());
		}
		return tmp;
	}

	public BufferedImage getField() {					
		//System.out.println("->[:Map].getField()");
		
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

}
