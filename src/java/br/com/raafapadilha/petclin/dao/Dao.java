package br.com.raafapadilha.petclin.dao;

import br.com.raafapadilha.petclin.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author RaafaPadilha
 */
public abstract class Dao<T> {

	private final Connection conn;

	public Dao() throws SQLException {
		conn = ConnectionFactory.getConnection();
	}

	public Connection getConnection() {
		return conn;
	}

	public void closeConnection() throws SQLException {
		conn.close();
	}

	public abstract void create(T entity) throws SQLException;

	public abstract void update(T entity) throws SQLException;

	public abstract void delete(T entity) throws SQLException;

	public abstract List<T> findAll() throws SQLException;

	public abstract T findById(Long id) throws SQLException;
}
