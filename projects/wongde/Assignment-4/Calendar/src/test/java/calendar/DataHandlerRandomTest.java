package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"deleteAppt","getApptRange"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
        }
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
        }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
        }	
   /**
     * Generate Random Tests that tests Appt Class.
     */
	 @Test
	  public void radnomtest()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
				
				 int startHour=ValuesGenerator.getRandomIntBetween(random, 0, 24);
				 int startMinute=ValuesGenerator.getRandomIntBetween(random, 0, 60);
				 int startDay=ValuesGenerator.getRandomIntBetween(random, 1, 29);
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 13);
				 int startYear=ValuesGenerator.getRandomIntBetween(random, 0, 2020);
				 String title=(String) ValuesGenerator.getString(random);
				 String description=(String) ValuesGenerator.getString(random);
				 String emailAddress=(String) ValuesGenerator.getString(random);

				 //Construct a new Appointment object with the initial data	 
				 //Construct a new Appointment object with the initial data	 
		         Appt appt = new Appt(startHour,
		                  startMinute ,
		                  startDay ,
		                  startMonth ,
		                  startYear ,
		                  title,
		                 description,
		                 emailAddress);
				 appt.setValid();
				GregorianCalendar day1 = new GregorianCalendar(startYear, startMonth - 1, startDay);
						 
				long randomseed2 = randomseed + 1;
				Random random2 = new Random(randomseed);
				
				 int startHour2=ValuesGenerator.getRandomIntBetween(random2, 0, 24);
				 int startMinute2=ValuesGenerator.getRandomIntBetween(random2, 0, 60);
				 int startDay2=ValuesGenerator.getRandomIntBetween(random2, 1, 29);
				 int startMonth2=ValuesGenerator.getRandomIntBetween(random2, 1, 13);
				 int startYear2=ValuesGenerator.getRandomIntBetween(random2, 0, 2020);
				 String title2=(String) ValuesGenerator.getString(random2);
				 String description2=(String) ValuesGenerator.getString(random2);
				 String emailAddress2=(String) ValuesGenerator.getString(random2);

				 //Construct a new Appointment object with the initial data	 
				 //Construct a new Appointment object with the initial data	 
		         Appt appt2 = new Appt(startHour2,
		                  startMinute2 ,
		                  startDay2 ,
		                  startMonth2 ,
		                  startYear2 ,
		                  title2,
		                 description2,
		                 emailAddress2);
				 appt2.setValid();
				 GregorianCalendar day2 = new GregorianCalendar(startYear2, startMonth2 - 1, startDay2);
				 
				 String dataName = title + ".xml";
				 boolean aSave = ValuesGenerator.getBoolean(.5f, random);
				 DataHandler data0 = new DataHandler(dataName, aSave);
				 data0.saveAppt(appt);
				 data0.saveAppt(appt2);

			for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = DataHandlerRandomTest.RandomSelectMethod(random);
					   if (methodName.equals("deleteAppt")){
						   
						   Appt falseAppt = new Appt(startHour,
							  startMinute ,
							  startDay2 ,
							  startMonth ,
							  startYear2 ,
							  title2,
							 description2,
							 emailAddress2);
							 
							boolean result1 = data0.deleteAppt(appt); 
							boolean result2 = data0.deleteAppt(appt2); 
							 
							//result1
						   if (appt.getValid()){
							   assertTrue(result1);
						   }
						   else{
							   assertFalse(result1);
						   }
						   
						   //result2
						   if (appt2.getValid()){
							   assertTrue(result2);
						   }
						   else{
							   assertFalse(result2);
						   }
						   
						   //result3
						   assertFalse(data0.deleteAppt(falseAppt));
						   
						   
						}
						
						else if (methodName.equals("getApptRange")){
							
							GregorianCalendar day3 = new GregorianCalendar(startYear2, startMonth2 - 1, startDay2 + 1);
							
							LinkedList<CalDay> calDays = new LinkedList<CalDay>();
							calDays = (LinkedList<CalDay>) data0.getApptRange(day1,day2);
							
							CalDay calday= calDays.get(0);
							CalDay calday2 = new CalDay(day1);
							assertEquals( calday2.getFullInfomrationApp(calday2), calday.getFullInfomrationApp(calday));
							//input stuff here
						}
						
				}
				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}catch(NullPointerException e){
			
		}catch(DateOutOfRangeException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }


	


	
}
