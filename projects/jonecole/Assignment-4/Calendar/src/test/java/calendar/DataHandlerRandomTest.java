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


	
}
