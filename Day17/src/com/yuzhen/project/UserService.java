package com.yuzhen.project;

import java.sql.SQLException;

/**
 * handle User object and add encryption
 * 
 * @author yuzhen
 *
 */
public class UserService {

	public boolean findUser(User user) throws SQLException {
		// parse the user object to get password
		String password = user.getPassword();
		String username = user.getUsername();

		// encode password using MD5
//		String encodedPwd = MD5Utils.encode(password); //
		String encodedPwd = password;
		// save back to user object
		user.setPassword(encodedPwd);

		// call DAO layer to check SQL
		 UserDao ud  =  new UserDao();
		 User existUser = ud.findUser(username, encodedPwd);
		
		if(existUser != null)
		{
			return true;
		}else{
			return false;
		}
	}

}
