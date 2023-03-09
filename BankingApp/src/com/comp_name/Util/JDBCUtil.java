package com.comp_name.Util;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	
	private JDBCUtil()			//constructor creation
	{
		
	}

	//get connection object
	public static Connection getConnection() throws IOException, SQLException
	{
		FileInputStream fis=new FileInputStream("D:\\Ineuron programs\\BankingApp\\application.properties");
		Properties properties=new Properties();
		properties.load(fis);
		
		Connection connection=DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("uname"),properties.getProperty("pwd"));
		return connection;
	}
	//closing the resources
	public static void cleanUp(Connection connection,Statement pstmt,ResultSet rs) throws SQLException
	{
		if (connection != null) {
			connection.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if(rs!=null)
		{
			rs.close();
		}
		
	}
	
}
