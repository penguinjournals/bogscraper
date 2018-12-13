package com.penguinjournals;

import org.flywaydb.core.Flyway;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/bog", "bog", "bog").load();
        flyway.migrate();
        System.out.println( "Hello World!" );
    }
}
