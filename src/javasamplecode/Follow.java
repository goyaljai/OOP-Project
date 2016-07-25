package javasamplecode;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Follow {
	
		int check;String st;Connection conn;boolean flag=false;
		
		public Follow() throws Exception, IOException, SQLException 
		{
			MySqlConnector scon=new MySqlConnector();
			scon.OpenConnection();
			conn=scon.getConnection();
			conn.setAutoCommit(false);
		}
		
		void AddFollower(int User,int Follower) throws SQLException   
		{
			PreparedStatement ps = null;
			String INSERT_QUERY = "INSERT INTO FOLLOW(USER_ID,FOLLOWER_ID) VALUES (? , ?)";
			try
			{
				ps = conn.prepareStatement(INSERT_QUERY);
				ps.setInt(1,User);
				ps.setInt(2, Follower);
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
		
		boolean IsFollowing(int User,int Follower) throws SQLException
		{
			PreparedStatement ps = null;
			String INSERT_QUERY = "SELECT * FROM FOLLOW WHERE USER_ID = ? ";
			try
			{
				ps = conn.prepareStatement(INSERT_QUERY);
				ps.setInt(1,User);
			    ResultSet rs = null;
			    
			    rs= ps.executeQuery();
			    conn.commit();
			    
			    int user_id,follow_id;
			    
			    
			    while(rs.next())
			    {
			    	user_id = rs.getInt("USER_ID");
			    	follow_id = rs.getInt("FOLLOWER_ID");
			    	
			    	if(user_id == User && follow_id == Follower)
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
		
		
		List<User> ListOfFollowers(int User) throws SQLException
		{
		
			PreparedStatement ps = conn.prepareStatement
					("SELECT DISTINCT * FROM USER,FOLLOW WHERE FOLLOW.USER_ID=?"
							+ " AND USER.UID=FOLLOW.FOLLOWER_ID");
			
				ps.setInt(1,User);
				ResultSet rst = ps.executeQuery();
			    conn.commit();
			    List<User> list = new ArrayList<User>();
			    //int i=0;
			    while(rst.next())
			    {
			    	User user = new User();
					user.setUSERNAME(rst.getString("USERNAME"));
					user.setPROFILEPICTURE(rst.getString("PROFILEPICTURE"));
					user.setFNAME(rst.getString("FNAME"));
					user.setGENDER(rst.getString("GENDER"));
					user.setPASS(rst.getString("PASS"));
					user.setLNAME(rst.getString("LNAME"));
			    	list.add(user);
			    }
			
			return list;
			
		}
		
		
		
		void DeleteFollower(int User,int Follower) throws SQLException
		{
			
			PreparedStatement ps =conn.prepareStatement("DELETE FROM FOLLOW WHERE USER_ID=? AND FOLLOWER_ID=?");
			ps.setInt(1, User);
			ps.setInt(2, Follower);
			ps.executeUpdate();
			conn.commit();
			ps.close();
			
			
		}
		
		
		List<User> ListOfFollowings(int User) throws SQLException
		{
		
			PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM USER,FOLLOW WHERE FOLLOW.FOLLOWER_ID=? "
																		+ "AND USER.UID=FOLLOW.USER_ID");
			
				ps.setInt(1,User);
				ResultSet rst = ps.executeQuery();
			    conn.commit();
			    List<User> list = new ArrayList<User>();
			    //int i=0;
			    while(rst.next())
			    {
			    	User user = new User();
					user.setUSERNAME(rst.getString("USERNAME"));
					user.setPROFILEPICTURE(rst.getString("PROFILEPICTURE"));
					user.setFNAME(rst.getString("FNAME"));
					user.setGENDER(rst.getString("GENDER"));
					user.setPASS(rst.getString("PASS"));
					user.setLNAME(rst.getString("LNAME"));
			    	list.add(user);
			    }
			
			return list;
			
		}
		
}
