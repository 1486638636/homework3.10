package com.hand.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hand.dao.UserDao;
import com.hand.utils.Dbutils;

public class UserDaoImpl implements UserDao {

	public boolean findUser(String name, String password) {
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = Dbutils.getConnection();
		String sql = "select count(*) from User where name=? and password=?";
		boolean result = false;
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, password);
			rs = pre.executeQuery();			
			int count = 0;
			while (rs.next()) {
				 count=rs.getInt(1);
			}
			if(count==1){			
				result=true;
			}
			rs.close();
			pre.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dbutils.closeConnection();
		return result;
	}
}
