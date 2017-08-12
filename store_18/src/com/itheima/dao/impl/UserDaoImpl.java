package com.itheima.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;
import com.itheima.utils.C3P0Utils;

public class UserDaoImpl implements UserDao {

	@Override
	public void save(User user) throws Exception {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		/**
		 * 	`uid` varchar(32) NOT NULL,
	 `username` varchar(20) DEFAULT NULL,
	 `password` varchar(20) DEFAULT NULL,
     `name` varchar(20) DEFAULT NULL,
	 `email` varchar(30) DEFAULT NULL,
	 `telephone` varchar(20) DEFAULT NULL,
	 `birthday` date DEFAULT NULL,
	 `sex` varchar(10) DEFAULT NULL,
	 `state` int(11) DEFAULT NULL,
	 `code` varchar(64) DEFAULT NULL,
		 */
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getName(),
		user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()		
		};
		queryRunner.update(sql, params);
		
	}

	@Override
	public User findUserByCode(String code) throws Exception {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql ="select *from user where code = ?";
		return queryRunner.query(sql, new BeanHandler<User>(User.class),code);
	}

	@Override
	public void update(User user) throws Exception {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		
		/**
		 * 	`uid` varchar(32) NOT NULL,
	 `username` varchar(20) DEFAULT NULL,
	 `password` varchar(20) DEFAULT NULL,
     `name` varchar(20) DEFAULT NULL,
	 `email` varchar(30) DEFAULT NULL,
	 `telephone` varchar(20) DEFAULT NULL,
	 `birthday` date DEFAULT NULL,
	 `sex` varchar(10) DEFAULT NULL,
	 `state` int(11) DEFAULT NULL,
	 `code` varchar(64) DEFAULT NULL,
		String sql ="";*/
		
		String sql = "update user set username = ?,password = ?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid = ?";
		Object[] params = {user.getUsername(),user.getPassword(),user.getName(),
				user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()		
				,user.getUid()};
		queryRunner.update(sql, params);
	}

	@Override
	public User selectUserByUserNameAndPwd(String username, String password) throws Exception {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where username = ? and password = ?";
		
		return queryRunner.query(sql, new BeanHandler<User>(User.class),username,password);
	}

}
