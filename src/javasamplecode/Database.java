package javasamplecode;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.File
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Blob;

//import swings.User;
@SuppressWarnings("unused")

public class Database 
{	
	
	static private Connection conn = null;
	
	PreparedStatement ps = null;
	ResultSet rs=null;	
	

		public Set<User> searchWithUsername() throws ClassNotFoundException, SQLException {
	
			
			MySqlConnector scon=new MySqlConnector();
			scon.OpenConnection();
			conn=scon.getConnection();
		
			conn.setAutoCommit(false);
	
			Set<User> hashTable = new HashSet<>();
			PreparedStatement pstmt = null;
			try {
			pstmt = conn.prepareStatement("SELECT * FROM user");
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				do {

					User user = new User();
					user.setUSERNAME(rst.getString("USERNAME"));
					user.setPROFILEPICTURE(rst.getString("PROFILEPICTURE"));
					user.setFNAME(rst.getString("FNAME"));
					user.setGENDER(rst.getString("GENDER"));
					user.setPASS(rst.getString("PASS"));
					user.setLNAME(rst.getString("LNAME"));
					hashTable.add(user);
					conn.commit();
				} while(rst.next());
			}
		} catch (SQLException sqlExc) {
			sqlExc.printStackTrace();
		}
		finally
		{
			pstmt.close();
		}
		
		return hashTable;
	}
	
	
	public void SetData(User u) throws Exception, IOException, SQLException
	{
			FileInputStream fis = null;
			MySqlConnector scon=new MySqlConnector();
			scon.OpenConnection();
			conn=scon.getConnection();
		
			conn.setAutoCommit(false);
			String INSERT_PICTURE = "insert into USER (FNAME,LNAME,GENDER,USERNAME,PASS,PROFILEPICTURE) values (? , ? , ? , ? ,? ,? )";
		    try 
		    {
		     System.out.println("blobStr"+u.getPROFILEPICTURE());
		      File file = new File(u.getPROFILEPICTURE());
		      fis = new FileInputStream(file);
		      ps = conn.prepareStatement(INSERT_PICTURE);
		      ps.setString(1, u.getFNAME());
		      ps.setString(2, u.getLNAME());
		      ps.setString(3, u.getGENDER());
		      ps.setString(4, u.getUSERNAME());
		      ps.setString(5, u.getPASS());
		      ps.setBinaryStream(6, fis, (int) file.length());
		      ps.executeUpdate();
		      conn.commit();
		    } 
	
		    catch(NullPointerException e)
		    {
		    	//System.out.println("-");
		    }
		    
		    finally 
		    {
		      ps.close();
		      fis.close();
		    }

	}
	
	public boolean ValidateLogin(User u,MySqlConnector scon) throws Exception, IOException, SQLException
	{

		
		conn=scon.getConnection();
		conn.setAutoCommit(false);
		String pass; 
		
		String Validate_login = "Select * from USER where USERNAME=? ";
		
		ps = conn.prepareStatement(Validate_login);
		ps.setString(1,u.getUSERNAME());
		
		rs=ps.executeQuery();
		
		if(rs.next()==false || rs.getString("PASS")==null || rs.getString("USERNAME")==null)
		{
			return false;
		}
		
		conn.commit();
		//rs.next();
		pass=rs.getString("PASS");
		
		if(u.getPASS().equals(pass))
		 {
			
			 return true;
		 }
		 else
		 {
			 return false;
		 }
		 
		 
	}
	
	public boolean ValidateSign(User u,MySqlConnector scon) throws Exception, IOException, SQLException
	{
		conn=scon.getConnection();
		conn.setAutoCommit(false);
	
		if(u.getFNAME()=="Enter Your First Name" || u.getLNAME()=="Enter Your Last Name" || u.getUSERNAME()=="Enter your User Name"|| u.getPASS()=="Password"|| u.getPROFILEPICTURE()==null )
		{
			return true;
		}
		String Validate_Sign = "Select * from USER where USERNAME=? ";
		
		ps = conn.prepareStatement(Validate_Sign);
		ps.setString(1,u.getUSERNAME());
		
		rs=ps.executeQuery();
		
		conn.commit();
		
		if(rs.next()==false ||  rs.getString("USERNAME")==null)
		 {
			
			 return false;
		 }
		 else
		 {
			 return true;
		 }
		 
	}
		
	
}