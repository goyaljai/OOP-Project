package javasamplecode;

//package javasamplecode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Blob;

import java.io.IOException;
public class CurrentUser extends User{
	
	Connection conn;
	String CurrentUser;
	int Uid;
	Blob profile_picture;
	User u = new User();
	public CurrentUser(User u) throws Exception, IOException, SQLException 
	{
		this.u = u;
		CurrentUser=u.getUSERNAME();
		MySqlConnector scon=new MySqlConnector();
		scon.OpenConnection();
		conn=scon.getConnection();
		conn.setAutoCommit(false);
	}
	
	public void setCurrentUser()
	{
		this.CurrentUser = u.getUSERNAME();
	}
	
	public String getCurrentUser()
	{
		return CurrentUser;
	}

	public int getUid() throws SQLException
	{
		PreparedStatement ps= null;
		ResultSet rs;
		String QUERY = "SELECT UID FROM USER WHERE USERNAME = ? ";
		try
		{
			ps = conn.prepareStatement(QUERY);
			ps.setString(1, CurrentUser);
			rs = ps.executeQuery();
			conn.commit();
			
			//System.out.println(CurrentUser);
			while(rs.next())
			{
				Uid = rs.getInt("UID");
			}
		}
		
		catch(Exception e)
		{
			
		}

		return Uid; 
	}
	
	public Blob getProfilepicture() throws SQLException
	{
		PreparedStatement ps= null;
		
		ResultSet rs;
		String QUERY = "SELECT PROFILEPICTURE FROM USER WHERE USERNAME = ? ";
		try
		{
			ps = conn.prepareStatement(QUERY);
			ps.setString(1, CurrentUser);
			rs = ps.executeQuery();
			conn.commit();
			
			
			while(rs.next())
			{
				profile_picture = (Blob) rs.getBlob("PROFILEPICTURE");
			}
			
		}
		
		catch(Exception e)
		{
			
		}
		

		
		return profile_picture;
		
	}
	
	public User getAllData(User u) throws SQLException
	{
		
		PreparedStatement ps= null;
		ResultSet rs;
		String QUERY = "SELECT * FROM USER WHERE USERNAME = ?";
		try
		{
			ps = conn.prepareStatement(QUERY);
			ps.setString(1, CurrentUser);
			rs = ps.executeQuery();
			conn.commit();
			
			//System.out.println(CurrentUser);
			while(rs.next())
			{
				u.setFNAME(rs.getString("FNAME"));
				u.setGENDER(rs.getString("GENDER"));
				u.setLNAME(rs.getString("LNAME"));
				u.setPASS(rs.getString("PASS"));
				u.setPROFILEPICTURE(rs.getString("PROFILEPICTURE"));
				u.setUID(rs.getInt("UID"));
			}
		}
		
		catch(Exception e)
		{
			
		}
		

		//System.out.println(Uid);
		
		return u;
	}
	
	

}
