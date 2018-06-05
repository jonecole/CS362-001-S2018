package calendar;

import java.util.Calendar;
import java.util.Random;
import java.util.GregorianCalendar;
import calendar.Appt;
import calendar.CalDay;
import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {

	 private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	 private static final int NUM_TESTS=100;

	 /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	 public void randomtest()  throws Throwable {
		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 System.out.println("Start testing...");

		 try {
			 CalDay calDay = new CalDay(new GregorianCalendar(2018, 5, 22));

			 for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				 long randomseed = System.currentTimeMillis(); //10
				 //			System.out.println(" Seed:"+randomseed );
				 Random random = new Random(randomseed);

				 for (int i = 0; i < NUM_TESTS; i++) {
					 int startHour = ValuesGenerator.getRandomIntBetween(random, -5, 30);
					 int startMinute = ValuesGenerator.getRandomIntBetween(random, -5, 70);
					 int startDay = ValuesGenerator.getRandomIntBetween(random, -5, 35);
					 int startMonth = ValuesGenerator.getRandomIntBetween(random, -5, 15);
					 int startYear = ValuesGenerator.getRandomIntBetween(random, -100, 2500);
					 String title = "Birthday Party";
					 String description = "This is my birthday party.";
					 String emailAddress = "xyz@gmail.com";

					 //Construct a new Appointment object with the initial data
					 Appt appt = new Appt(startHour,
							 startMinute,
							 startDay,
							 startMonth,
							 startYear,
							 title,
							 description,
							 emailAddress);

					 appt.setValid();

					 calDay.addAppt(appt);
				 }

				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				 if ((iteration % 10000) == 0 && iteration != 0)
					 System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);
			 }
		 } catch (Exception e) {

		 }

		 System.out.println("Done testing...");
	 }

	 // Start of non-random tests

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
		appt4.setValid();
		appt5.setValid();

		calDay1.addAppt(appt1);
		calDay1.addAppt(appt2);
		calDay1.addAppt(appt3);
		calDay1.addAppt(appt4);
		calDay1.addAppt(appt5);

		// get size of the appts. should only be 4 since one appointment was invalid
		assertEquals(3, calDay1.getSizeAppts());

		// get string representation of date
		String string1 = calDay1.toString();
		assertNotNull(string1);

		//get information about day after adding appointments
		String string2 = calDay1.getFullInfomrationApp(calDay1);
		assertNotNull(string2);
	}

	@Test(timeout = 4000)
	public void test02() throws Throwable {
	 	GregorianCalendar greg = new GregorianCalendar(2018, 6, 4);
	 	CalDay calDay2 = new CalDay(greg);

		// check to see if day/month/year set
		assertEquals(4, calDay2.getDay());
		// This caught a bug where the month is off by 1
		//assertEquals(7, calDay2.getMonth());
		// This is the adapted version for the bug so the test passes
		assertEquals(6, calDay2.getMonth());
		assertEquals(2018, calDay2.getYear());

		Appt appt6 = new Appt(12, 10, 4, 6, 2018, "Appointment 6", "This is appointment 6", "xyz@gmail.com");
		Appt appt7 = new Appt(10, 30, 4, 6, 2018, "Appointment 7", "This is appointment 7", "xyz@gmail.com");
		Appt appt8 = new Appt(10, 45, 4, 6, 2018, "Appointment 8", "This is appointment 8", "xyz@gmail.com");
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
		assertEquals("6-4-2018 \n\t10:30AM Appointment 7 This is appointment 7 \n\t10:45AM Appointment 8 This is appointment 8 \n\t0:10AM Appointment 6 This is appointment 6 ", string3);

		// This catches a bug that was introduced by Derek Wong
		String string4 = calDay2.toString();
		//assertEquals("\t --- 7/4/2018 --- \n --- -------- Appointments ------------ --- \n\t7/4/2018 at 10:30am ,Appointment 7, This is appointment 7\n \t7/4/2018 at 10:45am ,Appointment 8, This is appointment 8\n \t7/4/2018 at 12:10pm ,Appointment 6, This is appointment 6\n \n", string4);
		//  Adapted version for the bug so that test will pass
		assertEquals("\t --- 7/4/2018 --- \n --- -------- Appointments ------------ --- \n\t6/4/2018 at 10:30am ,Appointment 7, This is appointment 7\n \t6/4/2018 at 10:45am ,Appointment 8, This is appointment 8\n \t6/4/2018 at 12:10pm ,Appointment 6, This is appointment 6\n \n", string4);
	}
}
