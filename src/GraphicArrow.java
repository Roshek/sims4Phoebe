import java.awt.Graphics;


public class GraphicArrow implements Drawable{

	private Arrow arrow;
	
	public GraphicArrow(Arrow arrow){
		this.arrow=arrow;
	}
	
	public void draw(Graphics g) {
		/**
	      * Ket pont kozott rajzol egy nyilat 
	      * @param g grafikus komponens
	      */
		/**
	      * x1 x-pozicioja az startpoint-nak
	      * y1 y-pozicioja az startpoint-nak
	      * x2 x-pozicioja az endpoint-nak
	      * y2 y-pozicioja az endpoint-nak
	      */
	     	int x1=arrow.getStartPoint().getX();
	     	int y1=arrow.getStartPoint().getY();
	     	int x2=arrow.getEndPoint().getX();
	     	int y2=arrow.getEndPoint().getY();
	     	final int ARR_SIZE = 10;

                Graphics2D g1 = (Graphics2D) g.create();

                double dx = x2 - x1, dy = y2 - y1;
                double angle = Math.atan2(dy, dx);
                int len = (int) Math.sqrt(dx*dx + dy*dy);
                AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
                at.concatenate(AffineTransform.getRotateInstance(angle));
                g1.transform(at);

                g1.drawLine(0, 0, len, 0);
                g1.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                              new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
	}

}
