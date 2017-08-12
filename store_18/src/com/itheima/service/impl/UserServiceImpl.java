package com.itheima.service.impl;

import com.itheima.bean.User;
import com.itheima.constant.Constant;
import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.service.UserService;
import com.itheima.utils.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	public void regist(User user) throws Exception {

		// 调用Dao
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
		// 发送激活邮件      
		MailUtils.sendMail(user.getEmail(), "尊敬的" + user.getName() + ":欢迎注册黑马商城!请点击下面的链接,进行激活.<a href='http://localhost:8080/store_18/userServlet?method=active&code="+user.getCode()+"'>点击激活</a>");

	}

	@Override
	public User findUserByCode(String code) throws Exception {
		//调用Dao根据code获得User对象
		UserDao userDao =  new UserDaoImpl();
		User user =  userDao.findUserByCode(code);
		
		if(user != null){
			//把状态设置为1
			user.setState(Constant.USER_ACTIVED);
			//code设置为null
			user.setCode(null);
			//更新数据库
			userDao.update(user);
		}
		


		
		return user;
	}

	@Override
	public User login(String username, String password) throws Exception {
		//调用Dao  
		UserDao userDao =  new UserDaoImpl();
		User user =  userDao.selectUserByUserNameAndPwd(username,password);
		return user;
	}

}
