import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Stage extends JPanel implements ComponentListener{

	private Scene curScene;
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(curScene!=null)
			curScene.show(g);
	}

	public Stage() {
		setFocusable(true);
		addComponentListener(this);
	}
	public void setScene(Scene s) {
		curScene=s;
	}

	@Override
	public void componentResized(ComponentEvent event) {
		curScene=new MenuScene(new Point(5,5),new Dimension(getWidth()-10,getHeight()-10),this);
		new Timer(10,e->repaint()).start();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
}
