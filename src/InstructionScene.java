import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructionScene extends Scene{

	private final Color BORDER_COLOR=Color.RED,BG_COLOR=new Color(255, 221, 221);
	private Font font=new Font("Consolas",Font.BOLD,20);
	private final String instructionTxt="Use arrow keys or A,S,D,W to move and transform bricks. Press ESC to return to the menu.";
	private List<String> lines;
	public InstructionScene(Point p, Dimension d, Stage s) {
		super(p, d, s);
		lines=new ArrayList<>();
	}
	
	@Override
	protected void paint(Graphics g) {
		drawBorderAndBackground(BORDER_COLOR,BG_COLOR,2);
		g.setFont(font);
		g.setColor(Color.GREEN);
		if(lines.size()==0) {
			FontMetrics fm=g.getFontMetrics();
			String[] words=instructionTxt.split(" ");
			String line="";
			for (String word : words) {
				String s=line+" "+word;
				if(fm.stringWidth(s)>=getDimension().width) {
					lines.add(line);
					line=word;
				} else {
					line=s;
				}
			}
			lines.add(line);
		}
		int y=g.getFontMetrics().getHeight();
		for (String line : lines) {
			g.drawString(line,5,y);
			y+=g.getFontMetrics().getHeight()+5;
		}
	}

}
/*
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 @ Author: Chung
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */