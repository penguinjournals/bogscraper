package com.penguinjournals;

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
        Locale locale = new Locale("es", "ES");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, 'a' dd 'de' MMMM 'de' yyyy", locale);
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, 'a' dd 'de' MMMM 'de' YYYY", locale);
        String expected = "jueves, a 13 de diciembre de 2018";
        //String expected = "jueves, a 13 de diciembre de 2018";
        String actual = LocalDate.now().minusDays(9).format(dateTimeFormatter);
        System.out.println(actual);
        LocalDate localDate = LocalDate.parse(actual, dateTimeFormatter);
        assertEquals(expected, actual);
    }
}
