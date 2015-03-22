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

	/**\brief Bet�lti a kapott p�ly�t
	 * 
	 * A kapott string alapj�n megnyitja
	 * �s elt�rolja a k�pet.
	 * 
	 * @param s
	 */
	public void load(String s) {						//K�SZ sort of (nemtudom j�-e lol)
		System.out.println("->[:Map].load()");
	
		try {
			field=ImageIO.read(new File(s));
		} catch (IOException e) {
			System.out.println("No file with that name exists");
		}
	}

	/**\brief Megn�zi leesett-e a robot
	 * 
	 * Megn�zi a robot jelenlegi hely�t,
	 * �s ha az a p�ly�n a szakad�k sz�ne,
	 * akkor visszat�r true-val, jelezve,
	 * hogy a robot a m�lybe zuhant.
	 *
	 * @param c
	 */
	public Boolean fall(Coord c) {						//THIS IS SO BAD BUT I MIGHT WORK FOR TESTS
		System.out.println("->[:Map].fall(c)");
		////////////////////////////////////
		/*if(field.getRGB(c.getX(), c.getY()) == Color.BLACK.getRGB()){
			return true;
		}else
			return false;*/
		////////////////////////////////////
		
		
		System.out.println("? 4.2 Leesett a robot? (I/N)");		//Teszthez ez kell!
		Scanner in = new Scanner(System.in);
		String be = null;
		do{
        	be=in.nextLine();		        		
        	}while(!be.equals("I") && !be.equals("N"));
		if(be.equals("I"))
			return true;
		return false;
	}

	/**\brief Megadja a j�t�kosok kezd�helyeit
	 * 
	 * Leteszi a megfelel� sz�m� j�t�kost,
	 *  �s visszaadja a koordin�t�ikat 
	 *  tartalmaz� list�t.
	 * 
	 * @param numberOfPlayers
	 */
	public ArrayList<Coord> putPlayers(int numberOfPlayers) {			//TO BE FILLED LATER
		System.out.println("->[:Map].putPlayers(numberOfPlayers)");
		ArrayList<Coord> tmp=new ArrayList<Coord>();
		for(int i=0;i<numberOfPlayers;i++){						//�gy m�r nem dob hib�t az engine, nem �res list�b�l k�ri majd a j�t�kosok helyeit
			tmp.add(new Coord(0,0));
		}
		return tmp;
	}

	public BufferedImage getField() {					//K�SZ
		System.out.println("->[:Map].getField()");
		
		return field;
	}

	/**
	 * 
	 * @param field
	 */
	public void setField(BufferedImage field) {			//K�SZ
		System.out.println("->[:Map].setField()");
		
		this.field=field;
	}

}