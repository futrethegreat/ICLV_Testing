package com.ctc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to use to connect MariaDB database. 
 * Uses constants and variables from Utils class.
 * 
 * @author David Sauce
 *
 */

public class MariaDB {

	// JDBC driver name
	public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

	/**
	 * Method which creates a connection to a MariaDB database using parameters specified.
	 * 
	 * @param host
	 * @param port
	 * @param database
	 * @param user
	 * @param password
	 * @return connection to a MariaDB database
	 * @throws SQLException
	 */
	public Connection connectDatabase(String host, String port, String database, String user, String password)
			throws SQLException {

		String url = null;
		Connection connection = null;

		try {
			// Mariadb driver registration
			Class.forName("org.mariadb.jdbc.Driver");

			// URL created using parameters
			url = "jdbc:mariadb://" + host + ":" + port + "/" + database;

			// Connect to DB
			connection = DriverManager.getConnection(url, user, password);
			// boolean valid = connection.isValid(50000);
			// Functions.consoleMsg(valid ? "TEST OK" : "TEST FAIL");
		} catch (ClassNotFoundException ex) {
			Utils.consoleMsg("ERROR: Error al registrar el driver de MariaDB: " + ex);
			throw new SQLException();
		} catch (SQLException sqle) {
			Utils.consoleMsg("ERROR: Error al conectar con la base de datos de MariaDB(" + url + "): " + sqle);
			throw new SQLException();
		}
		return connection;
	}
}