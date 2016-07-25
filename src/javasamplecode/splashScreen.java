package javasamplecode;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class splashScreen {
	
	JWindow window = new JWindow();
	
	void make() throws InterruptedException
	{
		
		//JFrame window = new JFrame();
		//window.setSize(600, 400);
		window.getContentPane().add(
		    new JLabel("", new ImageIcon("C:\\Users\\Goyal Banna\\Desktop\\300.gif"), SwingConstants.CENTER));
		window.setBounds(350, 150, 600, 450);
		window.setVisible(true);
		
		Thread.sleep(3000);window.setVisible(false);
		
	}
	
	void close()
	{
		window.setVisible(false);
	}
	
	
	

}
