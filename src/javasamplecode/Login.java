package javasamplecode;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthLookAndFeel;


@SuppressWarnings("unused")
public class Login {
	public JTextField passwordText = new JTextField(20);
	public JTextField userText = new JTextField(20);
	public JTextField getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(JTextField passwordText) {
		this.passwordText = passwordText;
	}

	public JTextField getUserText() {
		return userText;
	}

	public void setUserText(JTextField userText) {
		this.userText = userText;
	}


	
	public static void main(String args[]) throws IOException, SQLException, Exception{
		 try {
	         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {    //Switch from METAL to Nimbus 2d graphics
	             if ("Nimbus".equals(info.getName())) {
	                 javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                 break;
	             }
	         }
			     } catch (ClassNotFoundException ex11) {
	         java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex11);
	     } catch (InstantiationException ex1) {
	         java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex1);
	     } catch (IllegalAccessException ex2) {
	         java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex2);
	     } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	         java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	     }
		
		 Login ISA = new Login();
		ISA.createGUI(ISA);
		 
	}
	

	void createGUI(Login ISA) throws ClassNotFoundException, SQLException, InterruptedException {

		new splashScreen().make();
		JFrame mainframe = new JFrame("Image Sharing App");
		MySqlConnector scon=new MySqlConnector();
		mainframe.setSize(600, 320);
				
		JLabel Login_Page_gif = new javax.swing.JLabel();
       
        JButton LoginButton = new javax.swing.JButton();
        
        JButton SignUpButton = new javax.swing.JButton();

        mainframe.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        mainframe.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        mainframe.setForeground(new java.awt.Color(51, 51, 255));
        mainframe.getContentPane().setLayout(null);
        mainframe.setVisible(true);
        
      
        ImageIcon ic = new ImageIcon("C:\\Users\\Goyal Banna\\Desktop\\300.jpg");
        Login_Page_gif.setIcon(ic);
        mainframe.add(Login_Page_gif);
        //Login_Page_gif.setBounds(10, 10, 170+90+90+20,170+90);
        Login_Page_gif.setBounds(8, 10, 400,268);
        
        mainframe.setResizable(false);
        passwordText.setFont(new java.awt.Font("Roboto", 0, 12));
        userText.setText("Enter Your Username");
        userText.setFont(new java.awt.Font("Roboto", 0, 12));
        mainframe.getContentPane().add(userText);
        userText.setBounds(210+90+90+20, 80-10, 140, 20+10);

        passwordText.setText("Password");
        mainframe.getContentPane().add(LoginButton);
        mainframe.getContentPane().add(passwordText);
        passwordText.setBounds(240+90+60+20, 110-10,130+10, 20+10);
        userText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(userText.getText().trim().equals(""))
				{
					userText.setText("Enter Your Username");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(userText.getText().equals("Enter Your Username"))
				{
					userText.setText("");
				}
					
				
			}
		});
        
        passwordText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(passwordText.getText().trim().equals(""))
				{
					passwordText.setText("Password");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
				if(passwordText.getText().equals("Password"))
				{
					passwordText.setText("");
				}
				
			}
		});

        LoginButton.setText("Login");
        LoginButton.setFont(new java.awt.Font("Roboto", 0, 12));
        SignUpButton.setFont(new java.awt.Font("Roboto", 0, 12));
        LoginButton.setBounds(209+90+90+30+20, 160-10,73+10, 23+10 );

        SignUpButton.setText("Signup");
        LoginButton.setIcon(new ImageIcon( new ImageIcon("C:\\Users\\Goyal Banna\\Desktop\\login.png").getImage().getScaledInstance(10,10,java.awt.Image.SCALE_SMOOTH)));
        mainframe.getContentPane().add(SignUpButton);
        SignUpButton.setBounds(240+90+90+20, 190-10, 73+10, 23+10);

        //mainframe.pack();
		passwordText.setVisible(true);
		ActionListener loginButtonListener = new LoginButtonListener(userText,passwordText,scon,ISA,mainframe);
		ActionListener registerButtonListener = new RegisterNewUserListener.RegisterButtonListener(scon);
		passwordText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginButton.doClick();
				//mainframe.setState(Frame.ICONIFIED);
			}
		});
		
		
		LoginButton.addActionListener(loginButtonListener);
		SignUpButton.addActionListener(registerButtonListener);
		
	}



}