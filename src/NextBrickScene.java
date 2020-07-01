import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import static config.Config.*;

public class NextBrickScene extends Scene{

	private final Color BORDER_COLOR=Color.RED,BG_COLOR=new Color(255, 221, 221);
	private BoardScene board;
	public NextBrickScene(Point p, Dimension d, Stage s,BoardScene bs) {
		super(p, d, s);
		board=bs;
	}

	private void drawGrid(Graphics g) {
		for (int i = BRICK_SIZE; i < getDimension().width; i+=BRICK_SIZE) {
			g.drawLine(i,0,i,getDimension().height-2);
		}
		for (int i = BRICK_SIZE; i < getDimension().height; i+=BRICK_SIZE) {
			g.drawLine(0,i,getDimension().width-2,i);
		}
	}
	@Override
	protected void paint(Graphics g) {
		drawBorderAndBackground(BORDER_COLOR,BG_COLOR,2);
		drawGrid(g);
		Brick next=board.getNextBrick();
		g.setColor(next.getColor());
		int r=(3-next.getShape().length)/2;
		int c=(4-next.getShape()[0].length)/2;
		for (int i = 0; i < next.getShape().length; i++) {
			for (int j = 0; j < next.getShape()[0].length; j++) {
				if(next.getShape()[i][j]) {
					g.fillRect((c+j)*BRICK_SIZE+2,(r+i)*BRICK_SIZE+2,BRICK_SIZE-3,BRICK_SIZE-3);
				}
			}
		}
	}

}
