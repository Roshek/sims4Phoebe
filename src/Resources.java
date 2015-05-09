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
			// TODO Auto-generated catch block
			System.out.println("Failed to load images");
		}
	}

	public static BufferedImage getRobot(Robot r) {
		//a kulonbozo jatekosok robotjai kulonbozo szinuek
		//robot=ImageIO.read(new File("robot"+(r.getID()-1)+".png"));
		//return robot;
		return robot[r.getID()-1];
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
