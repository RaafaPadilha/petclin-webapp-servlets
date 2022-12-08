package br.com.raafapadilha.petclin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author RaafaPadilha
 */
public class ConnectionFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);

	private static final String URL = "jdbc:mysql://localhost:3306/petclin?useSSL=false";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";
	private static Connection conn;

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException ex) {
			LOGGER.error("Database Driver Error!", ex);
		}

		return conn;
	}
}
