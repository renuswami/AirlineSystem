package com.java.alrs.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author Renu Swami(ax1009)
 *
 */

public class DB {

	public static final String URL = "jdbc:mysql://localhost:3306/airline";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
