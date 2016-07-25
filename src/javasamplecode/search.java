//package swings;
package javasamplecode;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Blob;
@SuppressWarnings("unused")
public class search implements KeyListener {

	//List<User> newList1 = null;
	JFrame frame;
	JTextField searchField;
	JPanel detailsPanel;
	Database db = new Database();
	JFrame usersTimeline;
	
	///static byte [] imgdata;
	//ImageLabel lblImage = new ImageLabel("i");
	//ImageIcon icon = new ImageIcon();
	public search(JFrame f)
	{
		this.usersTimeline=f;
		createGUI();
	}

	
	public void createGUI(){
		frame = new JFrame("Search With Username");
		frame.setSize(658, 500);
		
		JPanel topPanel = new JPanel(new GridBagLayout());
		
		JLabel topLabel = new JLabel("Enter: ");
		searchField = new JTextField(15);
		JButton tl = new JButton("Back");
		tl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				usersTimeline.setVisible(true);
				
				
			}
		});
		searchField.addKeyListener(this);
		
		topPanel.add(topLabel);
		topPanel.add(searchField);
		topPanel.add(tl);
		frame.add(topPanel,"North");
		detailsPanel = new JPanel();
		//detailsPanel.add(new JLabel("-->> Image Sharing App <<--"));
		JScrollPane js = new JScrollPane();
		js.setViewportView(detailsPanel);
		frame.add(js,"Center");
			frame.setVisible(true);

	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		String str = searchField.getText();
		Set<User> Table = new HashSet<User>();
		Set<User> newTable = new HashSet<User>();
		
		
		try {
			Table = db.searchWithUsername();} catch (ClassNotFoundException | SQLException e) {
				// this list contains all the usernames in the database
				e.printStackTrace();
			}
			String typedString =str;
			
			for(User user : Table)
			{//System.out.println(str);
				if(user.getUSERNAME().length()>=str.length())if(user.getUSERNAME().substring(0,str.length()).equals(str))
				{//System.out.println(user.getFNAME());
					newTable.add(user);
				}
				
			}
			
			
		
		
		try {
			updateCenterPanel(newTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	public void updateCenterPanel(Set<User> newTable) throws SQLException
	{
		detailsPanel.removeAll();
		
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
		
		
		
		//for each loop
		for(User user : newTable)
		{
			
			//if(FoundUser(user)){
			JButton button = new JButton(user.getUSERNAME());
			button.setPreferredSize(new Dimension(100,35));
			JPanel tempPanel = new JPanel();
			tempPanel.add(button);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0){
					try {
						frame.setVisible(false);
						new UsersTimeline().makeFrame(user,0,1);            // 0 for simple and 1 for logged in and nxt 0 for clicked on search and 1 for simple
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});CurrentUser u = null ;
			try {
				u=new CurrentUser(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Blob aBlob = u.getProfilepicture();
			
			InputStream is = null;
			try {
				is = aBlob.getBinaryStream(1, aBlob.length());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BufferedImage imag = null;
			try {
				imag = ImageIO.read(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image image = imag;
			ImageIcon icon =new ImageIcon(image.getScaledInstance(180, 130, Image.SCALE_DEFAULT));
			
			ImageIcon imageIcon = new ImageIcon(icon.getImage().getScaledInstance(180, 130, Image.SCALE_DEFAULT));
	        JLabel imageLabel = new JLabel();
	        imageLabel.setIcon(imageIcon);
	        tempPanel.add(imageLabel);detailsPanel.add(tempPanel);
	        /*JScrollPane js = new JScrollPane();
			js.setViewportView(detailsPanel);
			frame.add(js,"Center");*/
			frame.repaint();
			frame.setVisible(true);
			
			// JscrollPane
			// 
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		searchField.getText();
		
	}
	
}

	

