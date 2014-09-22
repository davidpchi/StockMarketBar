import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ControlPanel extends JPanel{

	JLabel drinkID_Label;
	JTextField drinkID;
	JLabel drinkNo_Label;
	JTextField drinkNo;
	JButton orderButton;
	
	MainPanel displayPanel;
	
	public ControlPanel(MainPanel panel) {		
		drinkID_Label = new JLabel("Enter drink ID:");
		drinkID = new JTextField(10);
		drinkNo_Label = new JLabel("Enter number of drinks:");
		drinkNo = new JTextField(10);
		orderButton = new JButton("Place Order!");
		orderButton.addActionListener(new myButtonListener());
		
		displayPanel = panel;
		
		add(drinkID_Label);
		add(drinkID);
		add(drinkNo_Label);
		add(drinkNo);
		add (orderButton);
		
		setPreferredSize(new Dimension(500,500));
	}
	
	private class myButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int drinkID_int = Integer.parseInt(drinkID.getText());
			int drinkNo_int = Integer.parseInt(drinkNo.getText());
			displayPanel.buyDrinks(drinkID_int, drinkNo_int);
		}
	}
}
