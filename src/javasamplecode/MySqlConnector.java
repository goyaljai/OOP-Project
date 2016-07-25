package javasamplecode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {


	public final String USERNAME = 	"root";
	public final String PASSWORD = 	"goyal";
	public final String CONN_STRING = "jdbc:mysql://localhost/project";
	static public Connection conn = null;
	
	
	public void OpenConnection() throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		 conn=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
		

	}
	
	public  Connection getConnection() throws ClassNotFoundException,SQLException  
	{
		
		return conn;
	}
	public void close() throws SQLException
	{
		conn.close();
	}
	
	
}
