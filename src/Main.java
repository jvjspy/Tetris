import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static config.Config.*;
public class Main extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(50,50,WINDOW_WIDTH,WINDOW_HEIGHT);
		contentPane = new Stage();
		setContentPane(contentPane);
	}

}
