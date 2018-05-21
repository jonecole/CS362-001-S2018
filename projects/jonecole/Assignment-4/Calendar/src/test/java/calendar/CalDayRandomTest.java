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
					 int startYear = ValuesGenerator.getRandomIntBetween(random, -100, 2018);
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


	
}
