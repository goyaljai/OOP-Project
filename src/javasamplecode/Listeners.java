package javasamplecode;
//package javasamplecode;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Listeners {

	public static class UploadButtonListener implements ActionListener {
		JTextField username,password;
		UsersTimeline UsersTimeLineFrame = new UsersTimeline();
		
		String dp_path;
		static User user = new User();

		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();	 
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png"); 
			fileChooser.addChoosableFileFilter(filter);
			
			int result = fileChooser.showSaveDialog(null); 
			 if(result == JFileChooser.APPROVE_OPTION){ 
				 
				 File selectedFile = fileChooser.getSelectedFile();
				 dp_path = selectedFile.getAbsolutePath();
				 //label.setIcon(ResizeImage(path));
			 
			
			 user.setPROFILEPICTURE(dp_path);
			 try {
				//user.setUID(new CurrentUser(user).getUid());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 }
			 else if(result == JFileChooser.CANCEL_OPTION){ 
				 dp_path =  null;
				 user.setPROFILEPICTURE(dp_path);
				 }

		}
	}
	
public static class UploadAndEditListener implements ActionListener{
		User user = new User();
		public UploadAndEditListener(User u) {
			// TODO Auto-generated constructor stub
			this.user = u;
		}
		 //static Image im = new Image();
		static images im = new images();
		
		
		
		
		JFrame UploadAndEditFrame = new JFrame();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
		UploadAndEditFrame.setSize(400,300);
		
        java.awt.GridBagConstraints gridBagConstraints = null;
       
		UploadAndEditFrame = new Listeners.UploadAndEditListener(user).placeComponents(UploadAndEditFrame,user,gridBagConstraints);
		
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 115;
       
		UploadAndEditFrame.setVisible(true);
	
	}
	
	public  JFrame placeComponents(JFrame UploadAndEditFrame,User u, java.awt.GridBagConstraints gridBagConstraints)
	{
		ButtonGroup ImagePrivacy = new javax.swing.ButtonGroup();
        JButton UploadButton = new javax.swing.JButton();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea CaptionArea = new javax.swing.JTextArea();
        JButton DoneButton = new javax.swing.JButton();
        JButton EditButton = new javax.swing.JButton();
        JRadioButton PrivacyPublicButton = new javax.swing.JRadioButton();
        JRadioButton PrivacyOnlyMeButton = new javax.swing.JRadioButton();

        UploadAndEditFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        UploadAndEditFrame.getContentPane().setLayout(new java.awt.GridBagLayout());

        UploadButton.setText("Upload");
        
        ActionListener uploadButtonListener = new UploadEditListener();
        UploadButton.addActionListener(uploadButtonListener);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 106;
        gridBagConstraints.ipady = 1;
        UploadAndEditFrame.getContentPane().add(UploadButton, gridBagConstraints);

        CaptionArea.setColumns(20);
        CaptionArea.setRows(5);
        CaptionArea.setText("Add Caption");
        jScrollPane1.setViewportView(CaptionArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 5;
        UploadAndEditFrame.getContentPane().add(jScrollPane1, gridBagConstraints);
        DoneButton.setText("Done");
           DoneButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//UsersTimeline UsersTimeLineFrame = new UsersTimeline();
				//JOptionPane.showMessageDialog(null, "L");
				im.setCAPTION(CaptionArea.getText());
				im.setIMAGE(UploadAndEditListener.im.getIMAGE());
				//System.out.println(u.getUSERNAME());
				CurrentUser gg = null;
				try {
					gg = new CurrentUser(u);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					im.setUID( gg.getUid());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {

					ImageFunctions imf = new ImageFunctions();
					imf.UploadImage(im);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Error!! Re-Login!! ");
					UploadAndEditFrame.dispose();
					UploadAndEditFrame.setVisible(false);
					String[] args = null;
					try {
						new Login();
						Login.main(args);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						UploadAndEditFrame.setVisible(false);
					}
				}UploadAndEditFrame.setVisible(false);
			}
		});


        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 75;
        UploadAndEditFrame.getContentPane().add(DoneButton, gridBagConstraints);

        
        EditButton.setText("Edit image");
        //im.setIMAGE(UploadEditListener.path);
        
        EditButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("UploadEditListener.path"+UploadEditListener.path);
				EditImage edit = new EditImage(UploadEditListener.path);
				try {
					edit.returnEditedImages();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JFrame UploadAndEditFrame = new JFrame();
				//UploadAndEditFrame.setSize(, arg1);
				JPanel panel = new JPanel(new GridLayout(5, 5,5,5));
				UploadAndEditFrame.setSize(800,800);
				UploadAndEditFrame.setVisible(true);
				//int i=0;
				Image image;
				
				for(int i=1;i<5;i++)
				{
					
					String sys = System.getProperty("user.home");
			         sys=sys.replace('\\', '/');
			        
			         //System.out.println();
			         try {
			        	 File input = new File("C://Users/"+sys.substring(9, sys.length())+"/Desktop/"+i+".jpg");
						image = ImageIO.read(input);
						int index = i;
						ImageIcon scaledImageIcon =new ImageIcon(image.getScaledInstance(400,800,Image.SCALE_DEFAULT));
						JLabel lbl = new JLabel("123");
						//System.out.println(image);
						lbl.setIcon(scaledImageIcon);
						panel.setSize(800,800);
						String syss=sys;
						panel.add(lbl);
						UploadAndEditFrame.add(panel);
						lbl.addMouseListener(new MouseListener() {
							
							@Override
							public void mouseReleased(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mousePressed(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseExited(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseEntered(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseClicked(MouseEvent arg0) {
								// TODO Auto-generated method stub
								im.setIMAGE("C://Users/"+syss.substring(9, syss.length())+"/Desktop/"+index+".jpg");
								UploadAndEditFrame.setVisible(false);
							}
						});
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//UploadAndEditFrame.add(panel);
				UploadAndEditFrame.setVisible(true);
				//edit.actionPerformed(arg0);
				
			}
		});
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 3;
        UploadAndEditFrame.getContentPane().add(EditButton, gridBagConstraints);

        ImagePrivacy.add(PrivacyPublicButton);
        PrivacyPublicButton.setText("Public ");
        PrivacyPublicButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            		
            	im.setACCESS_USERS("public");

        }
 });

       

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        UploadAndEditFrame.getContentPane().add(PrivacyPublicButton, gridBagConstraints);

        ImagePrivacy.add(PrivacyOnlyMeButton);
        PrivacyOnlyMeButton.setText("Only me");
    	PrivacyOnlyMeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            		
            	im.setACCESS_USERS("only me");

        }
 });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        UploadAndEditFrame.getContentPane().add(PrivacyOnlyMeButton, gridBagConstraints);

        //bindingGroup.bind();
		return UploadAndEditFrame;
	}
	

public static class UploadEditListener implements ActionListener {
	JTextField username,password;
	UsersTimeline UsersTimeLineFrame = new UsersTimeline();
	
	static String path;
	
	

	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
	
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png"); 

		fileChooser.addChoosableFileFilter(filter);
		
		 int result = fileChooser.showSaveDialog(null);
		 
		 if(result == JFileChooser.APPROVE_OPTION){ 
			 File selectedFile = fileChooser.getSelectedFile();
		path = selectedFile.getAbsolutePath(); //label.setIcon(ResizeImage(path));*/
		
		 
		 UploadAndEditListener.im.setIMAGE(path);
		 
		 }
		 else if(result == JFileChooser.CANCEL_OPTION){ 
			 path =  null;
			 UploadAndEditListener.im.setIMAGE(path);
			 }
		

		
	
	
}




}
}
}