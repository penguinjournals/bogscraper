package com.penguinjournals;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private static Config config = new Config();

    public static void main(final String[] args) {
        LOG.info("Database migrations");
        Flyway flyway = Flyway.configure().dataSource(config.getDatabaseConnection(), config.getDatabaseUser(), config.getDatabasePassword()).load();
        flyway.migrate();
        try {
            run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run(final String[] args) {
        LOG.info("Scrapping");
    }
}
