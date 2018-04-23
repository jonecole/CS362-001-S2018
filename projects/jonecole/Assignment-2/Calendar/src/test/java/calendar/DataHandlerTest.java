
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;


public class DataHandlerTest{

    @Test(timeout = 4000)
    public void test00()  throws Throwable  {
        DataHandler dh1 = new DataHandler("TestFileName", true);

        // create two days
        GregorianCalendar gc1 = new GregorianCalendar(2018, 8, 14);
        GregorianCalendar gc2 = new GregorianCalendar(2018, 8, 22);
        CalDay calDay1 = new CalDay(gc1);
        CalDay calDay2 = new CalDay(gc2);

        // add two appts to each day
        Appt appt1 = new Appt(5, 30, 14, 8, 2018, "Appointment 1", "This is appointment 1", "xyz@gmail.com");
        Appt appt2 = new Appt(17, 15, 14, 8, 2018, "Appointment 2", "This is appointment 2", "xyz@gmail.com");
        // invalid appt
        Appt appt3 = new Appt(25, 62, 14, 9, 2018, "Appointment 3", "This is appointment 3", "xyz@gmail.com");

        int[] recurDays = new int[3];
        recurDays[0] = 14;
        recurDays[1] = 18;
        recurDays[2] = 22;
        appt1.setRecurrence(recurDays, 4, 4, 2);
        appt2.setRecurrence(recurDays, 4, 4, 2);

        appt1.setValid();
        appt2.setValid();
        //appt3.setValid();

        assertTrue(dh1.saveAppt(appt1));
        assertTrue(dh1.saveAppt(appt2));

        // wrong order of days
        try {
            assertNull(dh1.getApptRange(gc2, gc1));
        } catch (DateOutOfRangeException e) {
            System.out.println("DateOutOfRangeException caught");
        }

        // correct order of days
        assertNotNull(dh1.getApptRange(gc1, gc2));

        // deleting an appt
        assertTrue(dh1.deleteAppt(appt1));
        // this appt shouldn't exist
        assertFalse(dh1.deleteAppt(appt3));
    }

    // no auto save
    @Test(timeout = 4000)
    public void test01()  throws Throwable  {
        // initialize datahandler
        DataHandler dh2 = new DataHandler("TestFileName", false);

        // create two days
        GregorianCalendar gc1 = new GregorianCalendar(2018, 6, 20);
        GregorianCalendar gc2 = new GregorianCalendar(2018, 8, 25);
        CalDay calDay1 = new CalDay(gc1);
        CalDay calDay2 = new CalDay(gc2);

        // create two appointments
        Appt appt1 = new Appt(5, 30, 14, 8, 2018, "Appointment 1", "This is appointment 1", "xyz@gmail.com");
        Appt appt2 = new Appt(10, 45, 26, 10, 2018, "Appointment 2", "This is appointment 2", "xyz@gmail.com");

        // set recurrence with no recurring days
        int[] recurDays = new int[1];
        recurDays[0] = 0;
        appt1.setRecurrence(recurDays, 7, 7, 7);

        assertTrue(dh2.saveAppt(appt1));
        assertTrue(dh2.saveAppt(appt2));

        // last day before first
        try {
            assertNull(dh2.getApptRange(gc2, gc1));
        } catch (DateOutOfRangeException e) {
            System.out.println("DateOutOfRangeException caught");
        }

        // normal
        assertNotNull(dh2.getApptRange(gc1, gc2));

        assertTrue(dh2.deleteAppt(appt1));
    }
}
