package com.iotbay.Dao;

import java.sql.Connection;

public abstract class DB {
	// protected String URL = "jdbc:mysql://localhost:3306/";
	protected String URL = "jdbc:mysql://mysql:3306/";  // 使用 mysql 作为主机名

	protected String db = "TravelWeb";
	protected String dbuser = "root";
	protected String dbpass =  "1234!@#$";
	protected String driver = "com.mysql.cj.jdbc.Driver";
	protected Connection conn;
}