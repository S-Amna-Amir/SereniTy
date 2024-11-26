package serenity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler implements PersistenceHandler
{
	static Connection connection2;

	public DBHandler() 
	{		
		connection2 = establishDatabaseConnection();
		System.out.println("Connection established");
	}

	public static Connection establishDatabaseConnection() 
	{
	    try {
	        // Load the database driver (if needed)
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        // Establish the connection
	        String url = "jdbc:mysql://localhost:3306/serenity";
	        String username = "root";
	        String password = "1234";
	        return DriverManager.getConnection(url, username, password);
	    } 
	    catch (ClassNotFoundException | SQLException e) 
	    {
	        e.printStackTrace();
	        return null; // If connection fails, return null
	    }
	}
	
	public void closeConnection() 
	{
	    if (connection2 != null) 
	    {
	        try 
	        {
	            connection2.close();
	            System.out.println("Database connection closed.");
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	}
}
