package javasamplecode;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
//import javax.swing.JTextField;
@SuppressWarnings("unused")

public class LoginButtonListener implements ActionListener {
	JTextField username,password;
	UsersTimeline UsersTimeLineFrame;
	MySqlConnector scon;
	JFrame fr;
	String name;
	public LoginButtonListener(JTextField username,JTextField password,MySqlConnector scon,Login ISA,JFrame mainframe) throws ClassNotFoundException, SQLException {
		this.username=username;
		this.password=password;
		
		
		this.scon = scon;
		scon.OpenConnection();
		ISA.userText.setText(username.getText());
		this.fr=mainframe;
	}

	public void actionPerformed(ActionEvent e) {
		
		User u = new User();
		u.setUSERNAME(username.getText());
		u.setPASS(password.getText());
		Database db = new Database();
		fr.dispose();	
		JFrame f =new JFrame();
		f.setVisible(true);
		f.setSize(400, 1);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
		    f.setLocation(x, y);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f.setTitle("Loading....");try {
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f.setTitle("Loading......");try {
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f.setTitle("Loading........");try {
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f.setTitle("Loading..........");try {
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f.setTitle("Loading............");try {
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f.setTitle("Loading..............");try {
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		f.setTitle("Loading... Wait for another 10 seconds...");try {
			if(db.ValidateLogin(u,scon)){UsersTimeLineFrame = new UsersTimeline();UsersTimeLineFrame.makeFrame(u,1,1);f.dispose();}
			else{
				JOptionPane.showMessageDialog(null,"Error");f.dispose();}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
	}
}