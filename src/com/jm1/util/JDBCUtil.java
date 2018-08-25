package com.jm1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/jm1?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
		String username = "root";
		String password = "123456";
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
		
	}
	
	public static void release(Statement statement, Connection conn)
	{
		if(statement != null)
		{
			try {
				statement.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		statement = null;
		
		if(conn != null)
		{
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		conn = null;
		
	}
	
	public static void release(ResultSet rs, Statement statement, Connection conn)
	{
		if(rs != null)
		{
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		rs = null;
		
		release(statement, conn);
	}
}
