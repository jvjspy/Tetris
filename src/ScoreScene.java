import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

public class ScoreScene extends Scene{

	private BoardScene board;
	private Font font=new Font("Consolas",Font.BOLD,50);
	private final Color BORDER_COLOR=Color.RED,BG_COLOR=new Color(255, 221, 221);
	public ScoreScene(Point p, Dimension d, Stage s,BoardScene bs) {
		super(p, d, s);
		board=bs;
	}

	@Override
	protected void paint(Graphics g) {
		drawBorderAndBackground(BORDER_COLOR,BG_COLOR,2);
		g.setFont(font);
		FontMetrics fm=g.getFontMetrics();
		String score=board.getScore()+"";
		g.drawString(score,(getDimension().width-fm.stringWidth(score))/2,getDimension().height*2/3);
	}

}
