import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public abstract class Resources {
	private static BufferedImage[] robot;
	private static BufferedImage miniRobot;
	private static BufferedImage oil;
	private static BufferedImage slime;
	private static BufferedImage map;
	
	
	/**\Brief betolti a szukseges file-okat
	 * 
	 * Minden statikus adattagot inicializal es betolti a szukseges kepeket,
	 * System.out.println()-nel jelzi ha nem sikerult ez valamiert
	 * Minden indulasnal betolti mind a negy robot kepet, nem csak a szuksegeseket
	 * 
	 */
	public static void load(){
		robot=new BufferedImage[5];
		try {
			for(int i=0;i<5;i++)
				robot[i]=ImageIO.read(new File("robot" + i +".png"));
			
			miniRobot=ImageIO.read(new File("miniRobot.png"));
			oil=ImageIO.read(new File("oil.png"));
			slime=ImageIO.read(new File("slime.png"));
			map=ImageIO.read(new File("map.png"));
		} catch (IOException e) {
			System.out.println(e + "Failed to load images");
			System.exit(1);
		}
	}
	
	/**\Brief Visszaadja az adott robot kepet
	 * 
	 * Elkeri r-tol az ID-jet es visszaadja a robot tomb megfelelo elemet
	 * 
	 * @param r
	 * @return
	 */
	public static BufferedImage getRobot(Robot r) {
		//a kulonbozo jatekosok robotjai kulonbozo szinuek
		//robot=ImageIO.read(new File("robot"+(r.getID()-1)+".png"));
		//return robot;
		//return robot[r.getID()-1];
		return robot[r.getID()];
	}
	

	public static BufferedImage getMiniRobot() {
		return miniRobot;
	}

	public static BufferedImage getOil() {
		return oil;
	}

	public static BufferedImage getSlime() {
		return slime;
	}

	public static BufferedImage getMap() {
		return map;
	}
	
}
