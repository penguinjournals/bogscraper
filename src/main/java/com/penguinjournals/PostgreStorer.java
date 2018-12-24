package com.penguinjournals;

import org.jooq.Field;
import org.jooq.impl.DSL;
import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.SQLDialect;
import org.jooq.impl.SQLDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

import static com.penguinjournals.jooq.tables.Document.DOCUMENT;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;


public class PostgreStorer {
    private static Config config = new Config();

    private static final String DATABASE_USER = config.getDatabaseUser();
    private static final String DATABASE_PASSWORD = config.getDatabasePassword();
    private static final String DATABASE_URL = config.getDatabaseConnection();
    private static final Logger LOG =  LoggerFactory.getLogger(PostgreStorer.class);

    public final void storeInfoPiece(final InfoPiece infoPiece) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            DataType<LocalDate> localDateDataType = SQLDataType.DATE.asConvertedDataType(new LocalDateConverter());
            Field localDateField = field(name(DOCUMENT.getName(), DOCUMENT.DATE.getName()), localDateDataType);
            DSLContext sql = DSL.using(connection, SQLDialect.POSTGRES);
            sql.insertInto(DOCUMENT)
                    .set(localDateField, infoPiece.getDate())
                    .set(DOCUMENT.INFO, infoPiece.getInfo())
                    .set(DOCUMENT.RAW, infoPiece.getRaw())
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
