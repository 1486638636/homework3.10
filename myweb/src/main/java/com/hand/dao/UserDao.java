package com.hand.dao;

import com.hand.entity.User;

public interface UserDao {
	public boolean findUser(String name,String password);
}
