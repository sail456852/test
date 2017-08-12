package com.itheima.dao;

import com.itheima.bean.User;

public interface UserDao {
	//保存用户
	void save(User user)  throws Exception;
	//根据code获得用户
	User findUserByCode(String code)throws Exception;
	//更新用户
	void update(User user)throws Exception;
	
	User selectUserByUserNameAndPwd(String username, String password)throws Exception;

}
