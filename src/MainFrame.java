import javax.swing.JFrame;

public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel test = new MainPanel();
		
		frame.add(test);
		
		frame.pack();
		frame.setVisible(true);
		
		JFrame controlFrame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ControlPanel controlPanel = new ControlPanel(test);
		
		controlFrame.add(controlPanel);
		controlFrame.pack();
		controlFrame.setVisible(true);
		
	}
}
