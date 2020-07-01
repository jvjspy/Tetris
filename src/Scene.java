import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Scene {
	private BufferedImage bi;
	private Graphics g;
	private Point pos;
	private Dimension dimen;
	private Stage stage;
	public Scene(Point p,Dimension d,Stage s) {
		pos=p;
		dimen=d;
		stage=s;
		bi=new BufferedImage(dimen.width,dimen.height,BufferedImage.TYPE_INT_RGB);
		g=bi.createGraphics();
	}
	protected void drawBorderAndBackground(Color bdColor,Color bgColor,int bdWidth) {
		g.setColor(bgColor);
		g.fillRect(0,0,getDimension().width,getDimension().height);
		g.setColor(bdColor);
		g.drawRoundRect(0,0,getDimension().width-bdWidth,getDimension().height-bdWidth,10,10);
	}
	protected abstract void paint(Graphics g);
	public void show(Graphics g) {
		paint(this.g);
		g.drawImage(bi,pos.x,pos.y,null);
	}
	public Point getPosition() {
		return pos;
	}
	public void setPosition(Point pos) {
		this.pos = pos;
	}
	public Dimension getDimension() {
		return dimen;
	}
	public void setDimension(Dimension dimen) {
		this.dimen = dimen;
		bi=new BufferedImage(dimen.width,dimen.height,BufferedImage.TYPE_INT_RGB);
		g=bi.createGraphics();
	}
	public Stage getStage() {
		return stage;
	}
}
