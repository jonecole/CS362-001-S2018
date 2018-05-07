/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.GregorianCalendar;


public class CalDayTest{

    // calls default constructor, checks validity. should not be valid
    @Test(timeout = 4000)
    public void test00()  throws Throwable  {
        CalDay calDay0 = new CalDay();
        assertFalse(calDay0.isValid());
        assertEquals(null, calDay0.iterator());

        // get string representation of date. should not be valid, so should not hit if loop in method
        String string0 = calDay0.toString();
        assertEquals("", string0);
    }

    // calls constructor with a calendar day, adds several appointments whose times are out of order
    @Test(timeout = 4000)
    public void test01()  throws Throwable  {
        CalDay calDay1 = new CalDay(new GregorianCalendar(2018, 9, 14));

        // get information about day while it's empty
        calDay1.getFullInfomrationApp(calDay1);

        Appt appt1 = new Appt(5, 30, 14, 9, 2018, "Appointment 1", "This is appointment 1", "xyz@gmail.com");
        Appt appt2 = new Appt(15, 30, 14, 9, 2018, "Appointment 2", "This is appointment 2", "xyz@gmail.com");
        Appt appt3 = new Appt(0, 5, 14, 9, 2018, "Appointment 3", "This is appointment 3", "xyz@gmail.com");
        // no time set on this appointment
        Appt appt4 = new Appt(14, 9, 2018, "Appointment 4", "This is appointment 4", "xyz@gmail.com");
        // this appointment is invalid
        Appt appt5 = new Appt(25, 62, 14, 9, 2018, "Appointment 5", "This is appointment 5", "xyz@gmail.com");
        // this appointment sets hour to 12, test for mutation

        appt1.setValid();
        appt2.setValid();
        appt3.setValid();
        //appt4.setValid(); // not working, bug?
        appt5.setValid();

        calDay1.addAppt(appt1);
        calDay1.addAppt(appt2);
        calDay1.addAppt(appt3);
        calDay1.addAppt(appt4);
        calDay1.addAppt(appt5);

        // get size of the appts. should only be 4 since one appointment was invalid
        assertEquals(4, calDay1.getSizeAppts());

        // get string representation of date
        String string1 = calDay1.toString();
        assertNotNull(string1);

        //get information about day after adding appointments
        String string2 = calDay1.getFullInfomrationApp(calDay1);
        assertNotNull(string2);
    }

    @Test(timeout = 4000)
    public void test02() throws Throwable {
        CalDay calDay2 = new CalDay(new GregorianCalendar(2018, 10, 15));

        // check to see if day/month/year set
        assertEquals(15, calDay2.getDay());
        assertEquals(10, calDay2.getMonth());
        assertEquals(2018, calDay2.getYear());

        Appt appt6 = new Appt(12, 10, 15, 10, 2018, "Appointment 6", "This is appointment 6", "xyz@gmail.com");
        Appt appt7 = new Appt(10, 30, 15, 10, 2018, "Appointment 7", "This is appointment 7", "xyz@gmail.com");
        Appt appt8 = new Appt(10, 45, 15, 10, 2018, "Appointment 8", "This is appointment 8", "xyz@gmail.com");
        /*
        int[] recurringDays = new int[4];
        recurringDays[0] = 16;
        recurringDays[1] = 17;
        recurringDays[2] = 18;
        recurringDays[3] = 19;
        appt6.setRecurrence(recurringDays, 1, 1, 4);
        */
        /*
        Appt appt7 = new Appt(12, 30, 15, 10, 2018, "Appointment 7", "This is appointment 7", "xyz@gmail.com");
        Appt appt8 = new Appt(10, 10, 15, 10, 2018, "Appointment 8", "This is appointment 8", "xyz@gmail.com");
        */

        calDay2.addAppt(appt6);
        calDay2.addAppt(appt7);
        calDay2.addAppt(appt8);

        assertTrue(appt6.hasTimeSet());

        String string3 = calDay2.getFullInfomrationApp(calDay2);
        assertEquals("10-15-2018 \n\t10:30AM Appointment 7 This is appointment 7 \n\t10:45AM Appointment 8 This is appointment 8 \n\t0:10AM Appointment 6 This is appointment 6 ", string3);

        String string4 = calDay2.toString();
        assertEquals("\t --- 11/15/2018 --- \n --- -------- Appointments ------------ --- \n\t10/15/2018 at 10:30am ,Appointment 7, This is appointment 7\n \t10/15/2018 at 10:45am ,Appointment 8, This is appointment 8\n \t10/15/2018 at 12:10pm ,Appointment 6, This is appointment 6\n \n", string4);
    }
}
