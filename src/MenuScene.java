import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuScene extends Scene{

	private Scene game=null;
	private int selection=1;
	private Font font=new Font("Consolas",Font.BOLD,50);
	private final String[] OPTIONS= {"RESUME","NEW GAME","ABOUT","EXIT"};
	public MenuScene(Point p, Dimension d, Stage s) {
		super(p, d, s);
		getStage().addKeyListener(new MenuController());
	}
	public MenuScene(Point p, Dimension d, Stage s,Scene game) {
		this(p,d,s);
		this.game=game;
	}
	@Override
	public void paint(Graphics g) {
		drawMenu(g);
	}
	private void drawMenu(Graphics g) {
		g.setFont(font);
		FontMetrics fm=g.getFontMetrics();
		int y=(getDimension().height-(fm.getHeight()+30)*OPTIONS.length)/2;
		for (int i = 0; i < OPTIONS.length; i++) {
			if(i==0&&game==null)	continue;
			g.setColor(Color.WHITE);
			if(selection==i)	g.setColor(Color.RED);
			g.drawString(OPTIONS[i],(getDimension().width-fm.stringWidth(OPTIONS[i]))/2,y);
			y+=fm.getHeight()+30;
		}
	}
	private class MenuController extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			if(key==KeyEvent.VK_UP&&(selection-1>0||selection-1==0&&game!=null)){
				selection--;
			} else if(key==KeyEvent.VK_DOWN&&selection+1<OPTIONS.length) {
				selection++;
			} else if(key==KeyEvent.VK_ENTER) {
				getStage().removeKeyListener(this);
				if(selection==0) {
					((GameScene)game).getBoardScene().setGameState(GameState.PLAYING);
					getStage().setScene(game);
				} else if(selection==1) {
					getStage().setScene(new GameScene(new Point(5,5),new Dimension(getStage().getWidth()-10,getStage().getHeight()-10),getStage()));
				} else if(selection==2) {
					
				} else if(selection==3) {
					System.exit(0);
				}
			}
		}
		
	}
}
