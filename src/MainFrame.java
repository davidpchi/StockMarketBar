import javax.swing.JFrame;

public class MainFrame extends JFrame{
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel test = new MainPanel();
		
		frame.add(test);
		
		frame.pack();
		frame.setVisible(true);
	}
}
