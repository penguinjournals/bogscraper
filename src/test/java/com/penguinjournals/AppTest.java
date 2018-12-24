package com.penguinjournals;

import com.google.gson.Gson;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        LocalDate announcementDate = LocalDate.now();
        String announcementRawText = "fake raw text";
        String announcementInfo = "{\"fakefield\": \"fakeFieldContent\"}";
        InfoPiece infoPiece = new InfoPiece(announcementDate,
                announcementInfo,
                announcementRawText);
        infoPiece.store();
        assertEquals(true, true);
    }
}
