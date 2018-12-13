package com.penguinjournals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private String databaseConnection;
    private String databaseUser;
    private String databasePassword;

    public Config() {
        Properties properties = new Properties();
        try (InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.databaseConnection = properties.getProperty("database.jdbc");
        this.databaseUser = properties.getProperty("database.user");
        this.databasePassword = properties.getProperty("database.password");
    }

    public String getDatabaseConnection() {
        return databaseConnection;
    }

    public void setDatabaseConnection(final String databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(final String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(final String databasePassword) {
        this.databasePassword = databasePassword;
    }
}
