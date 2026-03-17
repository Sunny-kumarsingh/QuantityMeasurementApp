package com.app.quantitymeasurement.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = ApplicationConfig.class
                             .getClassLoader()
                             .getResourceAsStream("application.properties"))
        {

            if (input == null) {
                throw new RuntimeException("application.properties not found in classpath");
            }

            props.load(input);
            } 
       
        catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public static String getDbUrl() {
        return props.getProperty("db.url");
    }

    public static String getDbUsername() {
        return props.getProperty("db.username");
    }

    public static String getDbPassword() {
        return props.getProperty("db.password");
    }

    public static int getPoolSize() {
        return Integer.parseInt(props.getProperty("db.pool.size"));
    }

    public static String getRepositoryType() {
        return props.getProperty("repository.type", "cache");
    }
}