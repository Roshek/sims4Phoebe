import java.awt.Graphics;


public class GraphicArrow implements Drawable{

	private Arrow arrow;
	
	public GraphicArrow(Arrow arrow){
		this.arrow=arrow;
	}
	
	public void draw(Graphics g) {
		g.drawLine(arrow.getStartPoint().getX(), arrow.getStartPoint().getY(), arrow.getEndPoint().getX(), arrow.getEndPoint().getY());
		
	}

}
