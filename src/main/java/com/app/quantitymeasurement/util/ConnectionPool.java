


package com.app.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                ApplicationConfig.getDbUrl(),
                ApplicationConfig.getDbUsername(),
                ApplicationConfig.getDbPassword()
        );
    }
}