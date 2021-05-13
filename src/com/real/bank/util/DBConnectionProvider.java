package com.real.bank.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.real.bank.service.TransactionServiceImpl;

public class DBConnectionProvider {
	private static final Logger LOGGER = Logger.getLogger(DBConnectionProvider.class);
	static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	private static Connection con;
	
	 
	public static Connection getConntection() throws ClassNotFoundException, SQLException, IOException {
		LOGGER.info("begins getConntection method");
		InputStream is = DBConnectionProvider.class.getClassLoader().getResourceAsStream("config.properties");
		System.out.println("Input Stream " + is);
		Properties props = new Properties();
		if(is !=null)
		{
			props.load(is);
		}

		if (threadLocal.get() == null) {

			Class.forName(props.getProperty("driver"));
			String url = props.getProperty("url");
			String userName = props.getProperty("userName");
			String password = props.getProperty("password");

			con = DriverManager.getConnection(url, userName, password);

			threadLocal.set(con);
		} else {
			con = threadLocal.get();
		}

		LOGGER.info("ends getConntection method");
		return con;
	}

	
	
	public static void closeConnection() throws SQLException {
		LOGGER.info("begins closeConnection method");
		if (con != null) {
			con.close();
			threadLocal.remove();
		}
		LOGGER.info("ends closeConnection method");
	}

}
