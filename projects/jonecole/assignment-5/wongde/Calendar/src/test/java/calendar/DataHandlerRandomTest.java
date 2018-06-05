package calendar;

import java.util.Calendar;
import java.util.Random;
import java.util.GregorianCalendar;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;
import org.junit.Test;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {

	 private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	 private static final int NUM_TESTS=1000000;

    /**
     * Generate Random Tests that tests DataHandler Class.
     */
	 @Test
	 public void randomtest()  throws Throwable  {
		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 System.out.println("Start testing...");

		 try {
			 for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				 long randomseed = System.currentTimeMillis(); //10
				 //			System.out.println(" Seed:"+randomseed );
				 Random random = new Random(randomseed);

				 for (int i = 0; i < NUM_TESTS; i++) {
					 DataHandler dh1 = new DataHandler("TestFileName", false);

					 // create two days
					 GregorianCalendar gc1 = new GregorianCalendar(2018, 8, 14);
					 GregorianCalendar gc2 = new GregorianCalendar(2018, 8, 22);
					 CalDay calDay1 = new CalDay(gc1);
					 CalDay calDay2 = new CalDay(gc2);

					 int day1 = ValuesGenerator.getRandomIntBetween(random, -5, 30);
					 int day2 = ValuesGenerator.getRandomIntBetween(random, -5, 30);
					 int month1 = ValuesGenerator.getRandomIntBetween(random, -5, 15);
					 int month2 = ValuesGenerator.getRandomIntBetween(random, -5, 15);

					 // add two appts to each day
					 Appt appt1 = new Appt(5, 30, day1, month2, 2018, "Appointment 1", "This is appointment 1", "xyz@gmail.com");
					 Appt appt2 = new Appt(17, 15, day2, month2, 2018, "Appointment 2", "This is appointment 2", "xyz@gmail.com");

					 int sizeArray=ValuesGenerator.getRandomIntBetween(random, -1, 8);
					 //int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
					 int[] recurDays = null;
					 int recur=ApptRandomTest.RandomSelectRecur(random);
					 int recurIncrement = ValuesGenerator.RandInt(random);
					 int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
					 appt1.setRecurrence(recurDays, recur, recurIncrement, recurNumber);

					 recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
					 appt2.setRecurrence(recurDays, recur, recurIncrement, recurNumber);

					 appt1.setValid();
					 appt2.setValid();

					 dh1.saveAppt(appt1);
					 dh1.saveAppt(appt2);

					 dh1.getApptRange(gc1, gc2);

					 dh1.deleteAppt(appt1);
					 dh1.deleteAppt(appt2);
				 }

				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				 if ((iteration % 10000) == 0 && iteration != 0)
					 System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);
			 }
		 } catch (Exception e) {

		 }
	 }

	 // Start of non-random testing

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
		appt3.setValid();

		assertTrue(dh1.saveAppt(appt1));
		assertTrue(dh1.saveAppt(appt2));
		assertFalse(dh1.saveAppt(appt3));

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
		assertEquals(true, dh1.deleteAppt(appt2));
		// this appt shouldn't exist
		assertEquals(false, dh1.deleteAppt(appt3));
	}

	// no auto save
	@Test(timeout = 4000)
	public void test01()  throws Throwable {

		// initialize datahandler
		DataHandler dh2 = new DataHandler("TestFileName", false);
		assertNotNull(dh2);

		// create two days
		GregorianCalendar gc1 = new GregorianCalendar(2018, 8, 14);
		GregorianCalendar gc2 = new GregorianCalendar(2018, 9, 2);

		// create two appointments
		Appt appt1 = new Appt(5, 30, 14, 8, 2018, "Appointment 1", "This is appointment 1", "xyz@gmail.com");
		Appt appt2 = new Appt(10, 45, 20, 10, 2018, "Appointment 2", "This is appointment 2", "xyz@gmail.com");
		// invalid date and time
		Appt appt3 = new Appt(25, 61, -1, 8, 2018, "Appointment 3", "This is appointment 3", "xyz@gmail.com");

		// set recurrence
		int[] recurDays = new int[1];
		recurDays[0] = 2;
		appt1.setRecurrence(recurDays, 1, 2, 1000);
		int[] recurDays2 = new int[0];
		appt2.setRecurrence(recurDays2, 0, 0, 0);

		appt1.setValid();
		appt2.setValid();
		appt3.setValid();

		assertTrue(appt1.getValid());
		assertTrue(appt2.getValid());
		assertFalse(appt3.getValid());

		// save appointments to datahandler
		assertTrue(dh2.saveAppt(appt1));
		assertTrue(dh2.saveAppt(appt2));
		assertFalse(dh2.saveAppt(appt3));

		// last day before first
		try {
			assertNull(dh2.getApptRange(gc2, gc1));
		} catch (DateOutOfRangeException e) {
			System.out.println("DateOutOfRangeException caught");
		}

		assertNotNull(dh2.getApptRange(gc1, gc2));
		System.out.println(dh2.getApptRange(gc1, gc2));

		assertTrue(dh2.deleteAppt(appt1));
		assertTrue(dh2.deleteAppt(appt2));
		assertFalse(dh2.deleteAppt(appt3));
	}
}
