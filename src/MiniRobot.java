import java.util.ArrayList;


public class MiniRobot extends Robot {

	private boolean onTrap=false;
	
	public MiniRobot(Engine engine) {
		super(engine);
		setRadius(10);
		setImpulse(new Coord (0,0));
		
	}

	public void move(){
		if (onTrap) 
			return;
		
		Coord trap = engine.getClosestTrap(position);//getClosestTrap(getPosition());
		if(trap!=null)
			System.out.println(trap.getX() + " " + trap.getY());
		System.out.println("distance: " + Coord.distance(trap, position));
		if(trap==null)
			return;
		if (Coord.distance(getPosition(), trap) <= 20) setPosition(trap);
		else {
			double d = Coord.distance(getPosition(), trap);
			
			double ratio = d / 20.0;
			ratio = 1 / ratio;
			
			double x = trap.getX()-getPosition().getX();
			double y = trap.getY()-getPosition().getY();
			
			x = x * ratio;
			y = y * ratio;
			
			setPosition(new Coord ((int)(x + position.getX()),(int)(y + position.getY())));
		}
		
		
	}
	
	
	public void steppedOnByRobot(Robot r){
		setAlive(false);
		placeOil();
	}
	
	public void steppedOnByMinirobot(MiniRobot x){
		x.setPosition(new Coord(x.getPosition().getX()+15,x.getPosition().getY()+15));
	}
	
	public Coord getClosestTrap(Coord position) {
		ArrayList<Trap> lt = getEngine().getTraps();
		Trap clsst = null;
		try
		{
			for (Trap it: lt) {
				if (Coord.distance(getPosition(), it.getPos()) < Coord.distance(getPosition(), clsst.getPos())) {
					clsst = it;
				}
			}
		
			return clsst.getPos();
		}
		catch (NullPointerException ex)
		{
			return null;
		}
	}
	
	
	public void setOnTrap(boolean onTrap) {
		this.onTrap = onTrap;
	}
	
	public boolean isOnTrap() {
		return onTrap;
	}

	
}
