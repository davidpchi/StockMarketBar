import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
	
	public final int myWidth = 500;
	public final int myHeight = 500;
	
    ArrayList<DrinkOrder> drinksList = new ArrayList<DrinkOrder>();
    
	Image upArrow;
	Image downArrow;
    Image neutral;
    
	public MainPanel() {
		setPreferredSize(new Dimension(myWidth, myHeight));
		
		addKeyListener(new myKeyListener());
		setFocusable(true);
		
		//upArrow = Toolkit.getDefaultToolkit().getImage(MainPanel.class.getResource("up.png")); 
		//downArrow = Toolkit.getDefaultToolkit().getImage(MainPanel.class.getResource("down.png")); 
		//neutral = Toolkit.getDefaultToolkit().getImage(MainPanel.class.getResource("neutral.png")); 

		BufferedImage img = null;
		try {
		    upArrow = ImageIO.read(new File("up.png"));
		    downArrow = ImageIO.read(new File("down.png"));
		    neutral = ImageIO.read(new File("neutral.png"));
		    
		    } catch (IOException e) {
		}
		
		// Location of file to read
        File file = new File("data.txt");
         
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                //read each line of the file
            	String line = scanner.nextLine();
            	//parse each line of the file into drinks
                String[] drinkData = line.split(" "); 
                String name = drinkData[0];
                double maxPrice = Double.parseDouble(drinkData[2]);
                double minPrice = Double.parseDouble(drinkData[1]);
                drinksList.add(new DrinkOrder(name, (maxPrice+minPrice)/2, minPrice, maxPrice));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        //print a list of all the drinks and prices
        for (int i = 0; i < drinksList.size(); i++) {
        	DrinkOrder temp = drinksList.get(i);
        	String name = temp.name;
        	double curPrice = temp.curPrice;
        	double minPrice = temp.minPrice;
        	double maxPrice = temp.maxPrice;
        	
        	System.out.println(i + ": " + name + " " + curPrice + " " + minPrice + " " + maxPrice);
        }
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, myWidth, myHeight);
		int x = 0;
		int y = 32;
				
        for (int i = 0; i < drinksList.size(); i++) {
        	DrinkOrder temp = drinksList.get(i);
        	String name = temp.name;
        	double curPrice = temp.curPrice;
        	double minPrice = temp.minPrice;
        	double maxPrice = temp.maxPrice;
        	int status = temp.status;
        	
        	String myText = (i + ": " + name + " " + curPrice);
        	FontMetrics metrics = g.getFontMetrics();
        	int textWidth = metrics.stringWidth(myText);
        	
        	if (status == 0) {
        		g.setColor(Color.cyan);
            	g.drawImage(neutral, textWidth + 10, y-16, null);
        	}
        	else if (status == 1) {
        		g.setColor(Color.green);
            	g.drawImage(upArrow, textWidth + 10, y-16, null);
        	}
        	else {
        		g.setColor(Color.red);
            	g.drawImage(downArrow, textWidth + 10, y-16, null);
        	}
           	
        	g.drawString(myText, x, y);
        	y = y+32;
        }
	}
	
	public void buyDrinks() {
        Scanner temp = new Scanner(System.in);
        System.out.println("ENTER DRINK SELECTION:");
        int selection = temp.nextInt();
    	System.out.println("HOW MANY DRINKS?");
    	int amount = temp.nextInt();
    	
    	buyDrinks(selection, amount);
	}
	
	public void buyDrinks(int drinkID, int drinkNo) {
        int selection = drinkID;
        if (selection >= drinksList.size()) {
        	System.out.println("INVALID SELECTION");
        }
        else {
        	int amount = drinkNo;
        	//increase the price for this individual drink, and decrease the price of others
        	for (int i = 0 ; i < drinksList.size(); i++) {
        		if (selection == i) {
        			drinksList.get(i).increasePrice(15);
        		}
        		else
        			drinksList.get(i).decreasePrice(15);
        	}
        }
        repaint();
	}
	
	private class myKeyListener implements KeyListener {
		public void keyPressed(KeyEvent arg0) {}

		public void keyReleased(KeyEvent arg0) {
			buyDrinks();
		}

		public void keyTyped(KeyEvent arg0) {}
	}

}
