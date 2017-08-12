package com.yuzhen.project;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.sun.org.apache.xml.internal.security.utils.JavaUtils;

public class UserDao {

	public  User findUser(String username,String pwdEncoded) throws SQLException {
		
		
		// find in SQL using QueryRunner
		DataSource cpds = JDBCUtils.getDataSource();
		QueryRunner qr = new QueryRunner(cpds);
		String sql = "select username,password from user where username=? and password = ? ";
		User userExtracted = qr.query(sql,new BeanHandler<User>(User.class),username,pwdEncoded); 
		
		return userExtracted;
		
	}
	
	
	public void saveUser(String username , String pwdEncoded) throws SQLException
	{
		DataSource cpds = JDBCUtils.getDataSource();
		QueryRunner qr = new QueryRunner(cpds);
		String sql = "insert into user set username = ? and password = ?";
		qr.update(sql,username,pwdEncoded);
	}
	
}
