import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import static config.Config.*;

public class BoardScene extends Scene{

	private Color[][] grid;
	private int score=0;
	private Queue<Integer> fullRows;
	private Font font=new Font("consolas",Font.BOLD|Font.ITALIC,50);
	private int space=ROWS;
	private long t0,lastDecrease;
	private Random rd;
	private GameScene gameScene;
	private GameState gameState=GameState.PLAYING;
	private Brick fallingBrick,nextBrick;
	private final Color BORDER_COLOR=Color.RED,BG_COLOR=new Color(255, 221, 221);
	public Brick getNextBrick() {
		return nextBrick;
	}
	public void setGameState(GameState s) {
		gameState=s;
	}
	public int getScore() {
		return score;
	}
	public BoardScene(Point p, Dimension d, Stage s,GameScene gs) {
		super(p, d, s);
		gameScene=gs;
		fullRows=new LinkedList<>();
		getStage().addKeyListener(new GameController());
		grid=new Color[ROWS][COLUMNS];
		rd=new Random(System.currentTimeMillis());
		fallingBrick=randomBrick();
		nextBrick=randomBrick();
	}
	private Brick randomBrick() {
		Brick b=new Brick(BRICK_SHAPES[rd.nextInt(BRICK_SHAPES.length)]);
		b.setColor(COLORS[rd.nextInt(COLORS.length)]);
		return b;
	}
	private boolean isRowFull(int r) {
		for (int c = 0; c < COLUMNS; c++) {
			if(grid[r][c]==null)
				return false;
		}
		return true;
	}
	private void addFullRowsToQueue() {
		for (int r = 0; r < ROWS; r++) {
			if(!fullRows.contains(r)&&isRowFull(r)) {
				for (int i = 0; i < COLUMNS; i++) {
					grid[r][i]=Color.BLACK;
				}
				fullRows.add(r);
			}
		}
	}
	private void decreaseFullRows(Graphics g) {
		if(!fullRows.isEmpty()&&System.currentTimeMillis()-lastDecrease>700) {
			lastDecrease=System.currentTimeMillis();
			int r=fullRows.poll();
			for (int i = r; i > 0 ; i--) {
				for (int j = 0; j < COLUMNS; j++) {
					grid[i][j]=grid[i-1][j];
				}
			}
			score++;
		}
	}
	private void drawGrid(Graphics g) {
		g.setColor(Color.RED);
		for (int i = BRICK_SIZE; i < getDimension().height; i+=BRICK_SIZE) {
			g.drawLine(0,i,getDimension().width-2,i);
		}
		for (int i = BRICK_SIZE; i < getDimension().width; i+=BRICK_SIZE) {
			g.drawLine(i,0,i,getDimension().height-2);
		}
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if(grid[i][j]!=null) {
					g.setColor(grid[i][j]);
					g.fillRect(j*BRICK_SIZE+2,i*BRICK_SIZE+2,BRICK_SIZE-3,BRICK_SIZE-3);
				}
			}
		}
	}
	private void drawFallingBrick(Graphics g) {
		fallingBrick.draw(g);
		if(System.currentTimeMillis()-t0>1000) {
			t0=System.currentTimeMillis();
			if(isValidMove(fallingBrick.getPosition().x+1,fallingBrick.getPosition().y))	fallingBrick.getPosition().x++;
			else {
				updateGrid();
				fallingBrick=nextBrick;
				nextBrick=randomBrick();
			}
		}
	}
	private void updateGrid() {
		space=Math.min(space,fallingBrick.getPosition().x);
		for (int i = 0; i < fallingBrick.getShape().length; i++) {
			for (int j = 0; j < fallingBrick.getShape()[0].length; j++) {
				if(grid[fallingBrick.getPosition().x+i][fallingBrick.getPosition().y+j]==null)
				grid[fallingBrick.getPosition().x+i][fallingBrick.getPosition().y+j]=fallingBrick.getShape()[i][j]?fallingBrick.getColor():null;
			}
		}
	}
	private void gameOver(Graphics g) {
		g.setFont(font);
		FontMetrics fm=g.getFontMetrics();
		String s="GAME OVER";
		g.setColor(Color.RED);
		g.drawString(s,(getDimension().width-fm.stringWidth(s))/2,getDimension().height/3);
	}
	@Override
	protected void paint(Graphics g) {
		drawBorderAndBackground(BORDER_COLOR,BG_COLOR,2);
		drawGrid(g);
		if(gameState==GameState.PLAYING) {
			drawFallingBrick(g);
			addFullRowsToQueue();
			decreaseFullRows(g);
			if(space<2)	gameState=GameState.GAMEOVER;
		} else if(gameState==GameState.GAMEOVER) {
			gameOver(g);
		}
	}
	private boolean isValidMove(int r,int c) {
		for (int i = 0; i < fallingBrick.getShape().length; i++) {
			for (int j = 0; j < fallingBrick.getShape()[0].length; j++) {
				if(fallingBrick.getShape()[i][j]&&((r+i>=ROWS||c+j<0||c+j>=COLUMNS)||r+i>=0&&grid[r+i][c+j]!=null))
						return false;
			}
		}
		return true;
	}
	private class GameController extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			if(gameState==GameState.PLAYING) {
				if(key==KeyEvent.VK_UP||key==KeyEvent.VK_W) {
					boolean[][] oldShape=fallingBrick.getShape();
					fallingBrick.setShape(fallingBrick.rotate());
					if(!isValidMove(fallingBrick.getPosition().x,fallingBrick.getPosition().y)) {
						fallingBrick.setShape(oldShape);
					}
				} else if((key==KeyEvent.VK_LEFT||key==KeyEvent.VK_D)&&isValidMove(fallingBrick.getPosition().x,fallingBrick.getPosition().y-1)) {
					fallingBrick.getPosition().y--;
				} else if((key==KeyEvent.VK_RIGHT||key==KeyEvent.VK_A)&&isValidMove(fallingBrick.getPosition().x,fallingBrick.getPosition().y+1)) {
					fallingBrick.getPosition().y++;
				} else if((key==KeyEvent.VK_DOWN||key==KeyEvent.VK_S)&&isValidMove(fallingBrick.getPosition().x+1,fallingBrick.getPosition().y)) {
					fallingBrick.getPosition().x++;
				} else if(key==KeyEvent.VK_ESCAPE) {
					gameState=GameState.PAUSED;
					getStage().setScene(new MenuScene(new Point(5,5),new Dimension(getDimension().width-10,getDimension().height-10),getStage(),gameScene));
				}
			}
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