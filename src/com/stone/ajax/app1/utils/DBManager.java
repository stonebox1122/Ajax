package com.stone.ajax.app1.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBManager {

	public static DataSource dataSource;
	
	static {
		dataSource = new ComboPooledDataSource("oracle");
	}
	
	public Connection getConnection() {
		Connection conn = null;
		if (dataSource != null) {
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	private DBManager() {}
	
	private static DBManager instance = new DBManager();
	
	public static DBManager getInstance() {
		return instance;
	}
}

