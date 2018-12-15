package com.penguinjournals;

import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

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
