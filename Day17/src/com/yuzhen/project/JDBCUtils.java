package com.yuzhen.project;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	//只创建一个连接池
	private static DataSource cpds = new ComboPooledDataSource();
	
	public static DataSource getDataSource() {
		return cpds;
	}
}
