package javasamplecode;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Blob;
//import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import java.io.*;

public class ImageFunctions extends images 
{
	
	Connection conn;int like_count;List<images> imgList = new ArrayList<images>();List<images> tempList = new ArrayList<images>();
	//Stack st = new Stack();
	
	public ImageFunctions() throws Exception, IOException, SQLException
	{
		MySqlConnector scon=new MySqlConnector();
		scon.OpenConnection();
		conn=scon.getConnection();
		conn.setAutoCommit(false);
	
		
	}
	
	void UploadImage(User u,images im) throws SQLException, IOException,Exception
	{
		//String Image=im.getIMAGE();
		String Caption = im.getCAPTION();
		String Access_Users = im.getACCESS_USERS();
		int Uid = u.getUID();

		Blob blob  = (Blob) im.getaBlob();
		String IMAGE_DETAILS = " INSERT INTO IMAGE (IMAGE,CAPTION,ACCESS_USERS,UID) VALUES(? ,? ,? ,? )";
				
			
		    PreparedStatement ps = conn.prepareStatement(IMAGE_DETAILS);
		    
		   // ps.setBinaryStream(1,blob);
		    ps.setBlob(1, blob);
		    ps.setString(2, Caption);
		    ps.setString(3, Access_Users);
		    ps.setInt(4, Uid);
		    ps.executeUpdate();
		    conn.commit();
		    
	}
	
	
	void UploadImage(images im) throws Exception, IOException, SQLException
	{
	
			String Image=im.getIMAGE();
			String Caption = im.getCAPTION();
			String Access_Users = im.getACCESS_USERS();
			int Uid = im.getUID();
			FileInputStream fis = null;
			PreparedStatement ps = null;
			String IMAGE_DETAILS = " INSERT INTO IMAGE (IMAGE,CAPTION,ACCESS_USERS,UID) VALUES(? ,? ,? ,? )";
			try
			{
						
				File file = new File(Image);
				fis = new FileInputStream(file);
			    ps = conn.prepareStatement(IMAGE_DETAILS);
			    
			    ps.setBinaryStream(1,fis, (int) file.length());
			    ps.setString(2, Caption);
			    ps.setString(3, Access_Users);
			    ps.setInt(4, Uid);
			    ps.executeUpdate();
			    conn.commit();
			}
			
			catch(NullPointerException e)
			{
				//System.out.println("hd");
			}
			
			finally
			{
				ps.close();
				fis.close();
				
			}
			
	}
	
	void DownloadImage(images im) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String DOWNLOAD_QUERY = "SELECT IMAGE FROM IMAGE WHERE IMAGE_ID = ? ";
		try
		{
			//System.out.println("Indu ka swag "+im.getIMAGE_ID());
			ps=conn.prepareStatement(DOWNLOAD_QUERY);
			ps.setInt(1, im.getIMAGE_ID());
			
			rs = ps.executeQuery();
			conn.commit();
			
			//int i=0;
			String sys = System.getProperty("user.home");
			while(rs.next())
			{
				InputStream in = rs.getBinaryStream(1);
				
				
				sys=sys.replace('\\', '/');
				
				File location = new File(sys+"/Downloads");
				//OutputStream f = new FileOutputStream(new File("C:/Users/Harshdeep11/Downloads/Image"+Image_id+ ".jpg"));
				OutputStream f = new FileOutputStream(new File(location,"Picture"+im.getIMAGE_ID()+".jpg"));
				//i++;
				int c = 0;
				while ((c = in.read()) > -1) 
				{
					f.write(c);
				}JOptionPane.showMessageDialog(
			            null, "Downloaded to "+sys+"/Downloads");
				
				f.close();
				in.close();
			}
		}
		
		catch(Exception e)
		{
			//
		}
		
	}
	public int LikeImage(images im) throws Exception, IOException, SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;;
		String FETCH_LIKE_COUNT = "SELECT LIKE_COUNT FROM IMAGE WHERE IMAGE_ID = ? ";
		String SET_LIKE_COUNT = " UPDATE IMAGE SET LIKE_COUNT = ? WHERE IMAGE_ID = ?";
		try
		{
			ps=conn.prepareStatement(FETCH_LIKE_COUNT);
			ps.setInt(1, im.getIMAGE_ID());
			
			rs = ps.executeQuery();
			conn.commit();
			
			
			while(rs.next())
			{
				like_count = rs.getInt("LIKE_COUNT");
			}
			
			like_count++;
			ps = conn.prepareStatement(SET_LIKE_COUNT);
			ps.setInt(1, like_count);
			ps.setInt(2, im.getIMAGE_ID());
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
		return like_count;
	}
	
	public int UnlikeImage(images im) throws Exception, IOException, SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;;
		String FETCH_LIKE_COUNT = "SELECT LIKE_COUNT FROM IMAGE WHERE IMAGE_ID = ? ";
		String SET_LIKE_COUNT = " UPDATE IMAGE SET LIKE_COUNT = ? WHERE IMAGE_ID = ?";
		try
		{
			ps=conn.prepareStatement(FETCH_LIKE_COUNT);
			ps.setInt(1, im.getIMAGE_ID());
			
			rs = ps.executeQuery();
			conn.commit();
			
			
			while(rs.next())
			{
				like_count = rs.getInt("LIKE_COUNT");
			}
			
			like_count--;
			ps = conn.prepareStatement(SET_LIKE_COUNT);
			ps.setInt(1, like_count);
			ps.setInt(2, im.getIMAGE_ID());
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
		return like_count;
	}
	
	
	public List<images> DisplayImageData(User u,User loggedinUser) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		//System.out.println("lolololo"+u.getFNAME());
		String FETCH_IMAGE_DATA = "SELECT IMAGE,CAPTION,LIKE_COUNT,VIEW_COUNT,ACCESS_USERS,UID,IMAGE_ID FROM IMAGE WHERE UID = ? ";
		
		
		try
		{
			ps=conn.prepareStatement(FETCH_IMAGE_DATA);
			ps.setInt(1, u.getUID());//System.out.println("Jai chakka : "+u.getUID());
			
			rs = ps.executeQuery();
			conn.commit();
			
			
			while(rs.next())
			{images tempImg = new images();
				tempImg.setaBlob((Blob) rs.getBlob("IMAGE"));
				tempImg.setCAPTION(rs.getString("CAPTION"));
				tempImg.setLIKE_COUNT(rs.getInt("LIKE_COUNT"));
				tempImg.setVIEW_COUNT(rs.getInt("VIEW_COUNT"));
				tempImg.setACCESS_USERS(rs.getString("ACCESS_USERS"));
				tempImg.setUID(rs.getInt("UID"));
				tempImg.setIMAGE_ID(rs.getInt("IMAGE_ID"));
				
				
				
				//System.out.println(rs.getString("ACCESS_USERS"));
				if(rs.getString("ACCESS_USERS").equals("public"))imgList.add(tempImg);
				if(!rs.getString("ACCESS_USERS").equals("public") && loggedinUser.equals(u))imgList.add(tempImg);
			}
		}
		
		catch(Exception e)
		{
			
		}
		
		for(int i=0;i<imgList.size();i++)
		{
			tempList.add(i, imgList.get(imgList.size()-i-1));
		}
		
		return tempList;
	}
	
	void DeleteImage(int image_id) throws SQLException 
	{
		
		//int i=0;
		PreparedStatement ps = null;
		String DELETE_QUERY = " DELETE FROM IMAGE WHERE IMAGE_ID = ? ";
		
		try
		{
			ps = conn.prepareStatement(DELETE_QUERY);

			ps.setInt(1,image_id);
			ps.executeUpdate();
			conn.commit();
		}
		
		catch(Exception e)
		{
			
		}
		
		finally
		{
			ps.close();
			//conn.close();
		}
	}
	//void Display();

}
