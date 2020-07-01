import static config.Config.BRICK_SIZE;
import static config.Config.COLUMNS;
import static config.Config.ROWS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class GameScene extends Scene{

	private final Color BORDER_COLOR=Color.BLUE,BG_COLOR=Color.WHITE; 
	private Scene board,nextBrick,score,instruction;
	public BoardScene getBoardScene() {
		return (BoardScene) board;
	}
	public GameScene(Point p, Dimension d, Stage s) {
		super(p, d, s);
		board=new BoardScene(new Point(5,5),new Dimension(COLUMNS*BRICK_SIZE,ROWS*BRICK_SIZE),getStage(),this);
		nextBrick=new NextBrickScene(new Point(board.getPosition().x+board.getDimension().width+10,board.getPosition().y),new Dimension(BRICK_SIZE*4,BRICK_SIZE*3),getStage(),(BoardScene) board);
		score=new ScoreScene(new Point(board.getPosition().x+board.getDimension().width+10,board.getPosition().y+nextBrick.getDimension().height+10),new Dimension(BRICK_SIZE*4,BRICK_SIZE*3),getStage(),(BoardScene) board);
		instruction=new InstructionScene(new Point(board.getPosition().x+board.getDimension().width+10,score.getPosition().y+score.getDimension().height+10),new Dimension(BRICK_SIZE*4,getDimension().height-nextBrick.getDimension().height-score.getDimension().height-30),getStage());
	}

	@Override
	protected void paint(Graphics g) {
		drawBorderAndBackground(BORDER_COLOR,BG_COLOR,2);
		board.show(g);
		nextBrick.show(g);
		score.show(g);
		instruction.show(g);
	}

}
