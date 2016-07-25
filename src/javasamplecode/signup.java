package javasamplecode;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

//import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import javafx.scene.control.TextField;


//ISP IS SIGNUP PAGE
@SuppressWarnings("unused")


public class signup{   

	
      JLabel title, idLabel, nameLabel, genderLabel, photoLabel, userNameLabel;
      JTextField idField, nameField, genderField, photoField, usernameField;
      JButton registerButton, exitButton,updateButton,deleteButton,resetButton,refresh;

      JRadioButton male, female;
      ButtonGroup bg;
      MySqlConnector scon;
      JPanel panel;

      public signup(MySqlConnector scon) {
    	  JFrame mainframe = new JFrame();
    	  mainframe.setSize(600,500);
    	  JPanel frame = new JPanel();
    	  frame.setSize(500, 400);
    	  mainframe.setLayout(null);
    	  frame.setLayout(null);

            title = new JLabel(new ImageIcon("C:\\Users\\Goyal Banna\\Desktop\\11.jpg"));
          //  title.setFont(new java.awt.Font("Monotype Corsiva", 0, 26));

            title.setBounds(-80, 1, 500, 46);

            idLabel = new JLabel("First name");

            idLabel.setBounds(30+20, 50, 60+50, 30);
            

            nameLabel = new JLabel("Last Name"); 

            nameLabel.setBounds(30+20, 85, 60+50, 30);

            genderLabel = new JLabel("Gender"); 
            
            JLabel passWord = new JLabel("Password");
            
            passWord.setBounds(30+20, 230, 60+50, 30);
            frame.add(passWord);

            genderLabel.setBounds(30+20, 190, 60+50, 30);

            photoLabel = new JLabel("Profile Picture"); 

            photoLabel.setBounds(30+20, 155, 60+50, 30); 

            userNameLabel = new JLabel("User Name"); 
            userNameLabel.setBounds(30+20, 120, 60+50, 30);
           

            // Defining ID field
            idField = new JTextField("");
            idField.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(idField.getText().trim().equals(""))
					{
						idField.setText("Enter Your First Name");
					}
					
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if(idField.getText().equals("Enter Your First Name"))
					{
						idField.setText("");
					}
						
					
				}
			});

            idField.setBounds(95+50, 50, 130, 30);
            idField.setEnabled(true);

            // Defining Name field
            nameField = new JTextField(); 
            nameField.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(nameField.getText().trim().equals(""))
					{
						nameField.setText("Enter Your Second Name");
					}
					
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if(nameField.getText().equals("Enter Your Second Name"))
					{
						nameField.setText("");
					}
						
					
				}
			});
            nameField.setBounds(95+50, 85, 130, 30);         

            // Defining Gender Buttons
            male = new JRadioButton("Male");

            male.setBounds(95+50, 190, 60, 30);
           
            female = new JRadioButton("Female");

            female.setBounds(155+50, 190, 70, 30);            

            bg = new ButtonGroup(); 

            bg.add(male); 

            bg.add(female); 

            JTextField password = new JTextField();
            password.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(password.getText().trim().equals(""))
					{
						password.setText("Enter Your Password");
					}
					
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if(password.getText().equals("Enter Your Password"))
					{
						password.setText("");
					}
						
					
				}
			});
            password.setBounds(95+50, 230, 130, 30);
            frame.add(password);
            
            JButton upload = new JButton("Upload");
            upload = new JButton("UPLOAD"); 

            upload.setBounds(95+50, 155, 130, 30);          


            usernameField = new JTextField(""); 

            usernameField.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(usernameField.getText().trim().equals(""))
					{
						usernameField.setText("Enter Your User Name");
					}
					
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if(usernameField.getText().equals("Enter Your User Name"))
					{
						usernameField.setText("");
					}
						
					
				}
			});

            //usernameField.setBounds(95, 190, 130, 30);
            usernameField.setBounds(95+50, 120, 130, 30);


            frame.add(title);
            frame.add(upload);
            ActionListener uploadButtonListener = new Listeners.UploadButtonListener();
            upload.addActionListener(uploadButtonListener);

            frame.add(idLabel);

            frame.add(nameLabel);

            frame.add(genderLabel);

            frame.add(photoLabel);

            frame.add(userNameLabel);

            frame.add(idField);

            frame.add(nameField);

            frame.add(male);

            frame.add(female);

          //  frame.add(photoField);

            frame.add(usernameField);

            exitButton = new JButton("Exit"); 

            exitButton.setBounds(50, 300, 80, 25); 
            
            

            // Defining Register Button

            registerButton = new JButton("Register");

            registerButton.setBounds(110+35, 300, 100+30, 25);
            //JTextField idField, nameField, genderField, photoField, usernameField;
            
          

            ActionListener registerButtonListener = new RegisterNewUserListener(bg, idField, nameField, password, usernameField,scon);
       //     User user = new User();
         //   user = registerButtonListener.addinUser(bg, idField, nameField, password, usernameField);
            
            registerButton.addActionListener(registerButtonListener);


            exitButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					mainframe.dispose();
					
				}
			});
            exitButton.setFont(new java.awt.Font("Roboto", 0, 12));
            registerButton.setFont(new java.awt.Font("Roboto", 0, 12));
            frame.add(exitButton);

            frame.add(registerButton);
            
            frame.setBounds(120, 5, 600, 700);
            
            ImageIcon imageIcon = new ImageIcon("C:\\Users\\Goyal Banna\\Desktop\\300.gif");
    		Image image = imageIcon.getImage();
    		ImageIcon icon1 =new ImageIcon(image.getScaledInstance(800,600,Image.SCALE_DEFAULT));

    	    JLabel iconLabel = new JLabel();
    	    iconLabel.setIcon(icon1);
    	    icon1.setImageObserver(iconLabel);
    	    frame.add(iconLabel);
            //.add(frame);
            mainframe.add(frame);mainframe.add(iconLabel);
            mainframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            mainframe.setResizable(false);

            mainframe.setVisible(true);

      }


}