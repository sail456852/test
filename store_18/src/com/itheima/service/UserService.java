package com.itheima.service;

import com.itheima.bean.User;

public interface UserService {
	//注册
	void regist(User user)  throws Exception;
	//根据code获得用户
	User findUserByCode(String code)throws Exception;
	//登录
	User login(String username, String password)throws Exception;

}
