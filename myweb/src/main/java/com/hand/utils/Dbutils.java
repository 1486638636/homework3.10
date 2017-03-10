package com.hand.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbutils {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myjdbc", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;

	}

	public static void closeConnection() {
		Connection conn = null;
		conn = getConnection();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
