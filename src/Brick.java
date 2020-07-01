import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import static config.Config.*;

public class Brick {
	private boolean[][] shape;
	private Point pos;
	private Color color;

	public boolean[][] getShape() {
		return shape;
	}
	public void setShape(boolean[][] s) {
		shape=s;
	}
	public Brick(boolean[][] shape) {
		this.shape = shape;
		pos = new Point(-shape.length,COLUMNS/2);
		color = Color.BLACK;
	}

	public boolean[][] rotate() {
		int m = shape.length;
		int n = shape[0].length;
		boolean[][] res = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				res[i][j] = shape[m - j - 1][i];
			}
		}
		return res;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[0].length; j++) {
				if (shape[i][j]) {
					g.fillRect((pos.y + j) * BRICK_SIZE + 2, (pos.x + i) * BRICK_SIZE + 2, BRICK_SIZE - 3, BRICK_SIZE - 3);
				}
			}
		}
	}

	public Point getPosition() {
		return pos;
	}

	public void setPosition(Point pos) {
		this.pos = pos;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
