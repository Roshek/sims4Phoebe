import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public abstract class Resources {
	private static BufferedImage robot;
	private static BufferedImage miniRobot;
	private static BufferedImage oil;
	private static BufferedImage slime;
	private static BufferedImage map;
	
	public static void load(){
		try {
			robot=ImageIO.read(new File("robot.png"));
			miniRobot=ImageIO.read(new File("miniRobot.png"));
			oil=ImageIO.read(new File("oil.png"));
			slime=ImageIO.read(new File("slime.png"));
			map=ImageIO.read(new File("map.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to load images");
		}
	}

	public static BufferedImage getRobot() {
		return robot;
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
