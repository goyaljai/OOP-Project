package javasamplecode;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Like {
	int check;String st;Connection conn;boolean flag=false;
	
	public Like () throws Exception, IOException, SQLException 
	{
		
		MySqlConnector scon=new MySqlConnector();
		scon.OpenConnection();
		conn=scon.getConnection();
		conn.setAutoCommit(false);
	
		
	}
	


	boolean HasLiked(int User,int Image_id) throws SQLException
	{
		PreparedStatement ps = null;
		String INSERT_QUERY = "SELECT * FROM LIKER WHERE USER_ID = ? ";
		try
		{  // System.out.println("User:  "+User);
			//System.out.println("Image:  "+Image_id);
			ps = conn.prepareStatement(INSERT_QUERY);
			ps.setInt(1,User);
		    ResultSet rs = null;
		    
		    rs= ps.executeQuery();
		    conn.commit();
		    
		    int user_id,image_id;
		    
		    
		    while(rs.next())
		    {
		    	user_id = rs.getInt("USER_ID");
		    	image_id = rs.getInt("IMAGE_ID");
		    	
		    	if(user_id == User && image_id == Image_id)
		    	{
		    		flag=true;
		    		break;
		    	}
		    }
		    
		}
					
		catch(Exception e)
		{
			
		}
		


		return flag;
		
	}
	
	void DeleteLiker(int User,int Image_id) throws SQLException   
	{
		PreparedStatement ps = null;
		String INSERT_QUERY = "DELETE FROM LIKER WHERE USER_ID = ? AND IMAGE_ID = ? ";
		try
		{
			ps = conn.prepareStatement(INSERT_QUERY);
			ps.setInt(1,User);
			ps.setInt(2, Image_id);
			ps.executeUpdate();
		    conn.commit();
		}
		
		catch(Exception e)
		{
			//
			
		}
		
		finally
		{
			ps.close();
			
		}
	}
	
	void AddLiker(int User,int Image_id) throws SQLException   
	{
		PreparedStatement ps = null;
		String INSERT_QUERY = "INSERT INTO LIKER (USER_ID,IMAGE_ID) VALUES (? , ?)";
		try
		{
			ps = conn.prepareStatement(INSERT_QUERY);
			ps.setInt(1,User);
			ps.setInt(2, Image_id);
			ps.executeUpdate();
		    conn.commit();
		}
		
		catch(Exception e)
		{
			//
			
		}
		
		finally
		{
			ps.close();
			
		}
	}
	/*SELECT USERNAME FROM USER,LIKER WHERE IMAGE_ID=9 AND USER.UID=LIKER.USER_ID;*/
	
	String DisplayLikers(int Image_id)
	{
		StringBuilder sb = new StringBuilder();
		
		//System.out.println("jhngbfd");
		//ArrayList<String> arr = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String QUERY = "SELECT USERNAME FROM USER,LIKER WHERE IMAGE_ID=? AND USER.UID=LIKER.USER_ID";
		
		try
		{
			ps = conn.prepareStatement(QUERY);
			ps.setInt(1, Image_id);
			rs=ps.executeQuery();
			conn.commit();
			
			while(rs.next())
			{
				
				sb.append(rs.getString("USERNAME" )+ " , ");
			}
		}
		
		catch(Exception e)
		{
			
		}
		//System.out.println(sb.toString());
		String temp = sb.toString();
		return temp;
	}

	

}
