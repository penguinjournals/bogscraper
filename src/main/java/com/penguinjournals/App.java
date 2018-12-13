package com.penguinjournals;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {
    private static Config config = new Config();

    public static void main(final String[] args) {
        bootstrapDatabase();
        System.out.println("Hello World!");
    }

    private static void bootstrapDatabase() {
        Flyway flyway = Flyway.configure().dataSource(config.getDatabaseConnection(), config.getDatabaseUser(), config.getDatabasePassword()).load();
        flyway.migrate();
    }
}
