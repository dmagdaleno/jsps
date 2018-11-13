package com.dmagdaleno.store.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	private static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver"; 
	private static String DB_HOST = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL"; 
	private static String DB_USER = "RM79920"; 
	private static String DB_PASS = "260989";
	
	private static ConnectionManager instance = null;
	
	private ConnectionManager() {}
	
	public static ConnectionManager getInstance() {
		if(instance == null)
			instance = new ConnectionManager();
		return instance;
	}
	
	public Connection getConnection() {
		Connection conexao = null;
		try {
			Class.forName(DRIVER_NAME);
			
			conexao = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
}
