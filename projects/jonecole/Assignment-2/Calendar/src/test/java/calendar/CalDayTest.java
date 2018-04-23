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

}
