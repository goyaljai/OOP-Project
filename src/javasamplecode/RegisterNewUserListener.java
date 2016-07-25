package javasamplecode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RegisterNewUserListener implements ActionListener{

	
	static User user = new User();
	
	ButtonGroup bg;JTextField Firstname;JTextField Lastname;JTextField Password;JTextField Username;
	MySqlConnector scon;
	
	public RegisterNewUserListener(ButtonGroup bg,JTextField Firstname,JTextField Lastname,JTextField Password,JTextField Username,MySqlConnector scon) {
		// TODO Auto-generated constructor stub
		this.bg = bg;
		this.Firstname = Firstname;
		this.Lastname = Lastname;
		this.Password = Password;
		this.Username = Username;
		this.scon = scon;
		
		
		//JOptionPane.showMessageDialog(null,user.getFNAME()+"\n"+user.getPASS()+"\n"+user.getPROFILEPICTURE()+"\n"+user.getGENDER()+"\n"+user.getUSERNAME());
		
		
	}
	
	public RegisterNewUserListener(String ProfilePicture) {
		// TODO Auto-generated constructor stub
		user.setPROFILEPICTURE(ProfilePicture);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Enumeration<AbstractButton> allRadioButton=this.bg.getElements();  
        while(allRadioButton.hasMoreElements())  
        {  
           JRadioButton temp=(JRadioButton)allRadioButton.nextElement();  
           if(temp.isSelected())  
           {  
            //JOptionPane.showMessageDialog(null,"You select : "+); 
            user.setGENDER(temp.getText());
           }  
        }            
		
		
		user.setPROFILEPICTURE(Listeners.UploadButtonListener.user.getPROFILEPICTURE());

		user.setFNAME(this.Firstname.getText());
		user.setLNAME(this.Lastname.getText());
		user.setUSERNAME(this.Username.getText());
		user.setPASS(this.Password.getText());
		
		//Database db = new Database();
		try {
			
			Database db = new Database();
			System.out.println(user.getFNAME()+user.getLNAME()+user.getGENDER()+user.getPASS()+user.getPROFILEPICTURE()+user.getUSERNAME());
			if(!db.ValidateSign(user,scon))
			db.SetData(user);
			else JOptionPane.showMessageDialog(null,"ERROR");
				
			
			
			//JOptionPane.showMessageDialog(null,"Error");
			//Usertimeline.make();
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//JOptionPane.showMessageDialog(null,+"\n"++"\n"++"\n"++"\n"+);
		try {
			new UsersTimeline().makeFrame(user,1,1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public static class RegisterButtonListener implements ActionListener {
		MySqlConnector scon;
		
		public RegisterButtonListener(MySqlConnector scon) {
			this.scon = scon;
		}
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new signup(scon);
			
		}
	}
	
}
