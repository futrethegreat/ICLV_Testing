//STEP 1. Import required packages
package com.ctc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ctc.Utils;

public class MariaDB {

	// JDBC driver name and database URL

	public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

	public Connection connectDatabase(String host, String port, String database, String user, String password)
			throws SQLException {

		String url = null;
		Connection connection = null;

		try {
			// Registramos el driver de Mariadb
			Class.forName("org.mariadb.jdbc.Driver");

			url = "jdbc:mariadb://" + host + ":" + port + "/" + database;

			// Conectamos con la base de datos
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