package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.DatabaseException;
import com.app.quantitymeasurement.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {
	
	private void createTableIfNotExists(Connection conn) throws SQLException {
	    String sql = "CREATE TABLE IF NOT EXISTS quantity_measurement (" +
	            "id IDENTITY PRIMARY KEY," +
	            "operation VARCHAR(50)," +
	            "operand1 VARCHAR(255)," +
	            "operand2 VARCHAR(255)," +
	            "result VARCHAR(255)," +
	            "error VARCHAR(255)" +
	            ")";
	    try (var stmt = conn.createStatement()) {
	        stmt.execute(sql);
	    }
	}

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = "INSERT INTO quantity_measurement " +
                "(operation, operand1, operand2, result, error) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	
        	 createTableIfNotExists(conn);
            stmt.setString(1, entity.getOperation());
            stmt.setString(2, entity.getOperand1());
            stmt.setString(3, entity.getOperand2());
            stmt.setString(4, entity.getResult());
            stmt.setString(5, entity.getError());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error saving data", e);
        }
    }
}