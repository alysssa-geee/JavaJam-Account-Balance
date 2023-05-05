package com.bryant.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * DBProxy Follows the proxy pattern and encapsulates basic database functionality. 
 * Any complexity involved in database processing is hidden and encapsulated with 
 * an instance of DBProxy. It is expected that clients with a requirement to operate
 * on multiple databases simultaneously can and should use a unique instance of DBProxy
 * for each database.
 * @author Alexander Bean, Zach Cullison, Alyssa Gee, Veronica Wallace
 *
 */
public class DBProxy {
	private String jdbcURL; 
	private String driver; 
	private Connection connection = null; 
	
	private DBProxy() {
		// Do not allow default constructor to be used
	}
	
	/**
	 * DBProxy requires the parameters below in order to construct an instance. 
	 * The arguments passed to the parameters are persisted internally as 
	 * class level attributes
	 * @param jdbcURL
	 * @param driver
	 */
	public DBProxy(String jdbcURL, String driver) {
		this.jdbcURL = jdbcURL;
		this.driver = driver; 
	}
	
	/**
	 * executeSQL implements a simple command pattern where a prepared SQL statement
	 * can be passed for execution. Upon successful execution, a Result Set is returned to 
	 * the client. Should an exception be raised, it is forwarded back to the client.
	 * @param sqlStatement
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ResultSet executeSQL(String sqlStatement) throws SQLException, ClassNotFoundException {
		openConnection(); 
		
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sqlStatement);
		
		return result; 
	}
	
	private boolean isConnected() {
		boolean connected = true; 
		if(connection == null) {
			connected = false; 
		}
		return connected; 
	}
	
	private void openConnection() throws SQLException, ClassNotFoundException {
		if (!isConnected()) {
			Class.forName(driver);
			connection = DriverManager.getConnection(jdbcURL); 
		}
	}
	
	public void closeConnection() throws SQLException {
		if (!isConnected()) {
			connection.close();
			connection = null;
		}
	}

	/**
	 * Will attempt to close a database connection if the internal state indicates
	 * the associated database connection was left open. Since there is little that 
	 * can be done if an SQLException is raised, the stack trace will be printed on 
	 * the console
	 */
	public void finalize() {
		try {
			if(isConnected() ) {
				closeConnection(); 
				System.out.println("WARNING: DB closed by 'finalize', connection was left open!");
			}
		} catch (SQLException e ) {
			e.printStackTrace();
		}
	}
}
