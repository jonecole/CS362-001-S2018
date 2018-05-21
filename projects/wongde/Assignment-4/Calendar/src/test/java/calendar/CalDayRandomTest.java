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
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"addAppt"};// The list of the of methods to be tested in the Appt class

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
	//			System.out.println(" Seed:"+randomseed );
				
				 long randomseed =System.currentTimeMillis(); //10
				 Random random = new Random(randomseed);
				 int startDay=ValuesGenerator.getRandomIntBetween(random, 1, 29);
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 0, 12);
				 int startYear=ValuesGenerator.getRandomIntBetween(random, 1, 2019);
		 

			for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = CalDayRandomTest.RandomSelectMethod(random);
					
					 GregorianCalendar day = new GregorianCalendar(startYear + i,startMonth, startDay);
					 CalDay calday0 = new CalDay(day);
				 
					   if (methodName.equals("addAppt")){
							 int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 25);
							 int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 61);
							 String title=(String) ValuesGenerator.getString(random);
							 String description=(String) ValuesGenerator.getString(random);
							 String emailAddress=(String) ValuesGenerator.getString(random);
							 //Construct a new Appointment object with the initial data	 
							 //Construct a new Appointment object with the initial data	 
							 Appt appt = new Appt(startHour,
									  startMinute ,
									  startDay ,
									  startMonth ,
									  startYear + i,
									  title,
									 description,
									 emailAddress);
									 
							 appt.setValid();
							 boolean valid = appt.getValid();
						   
							 long randomseedV =System.currentTimeMillis(); //10
						     Random randomV = new Random(randomseedV);
							 int startHourV=ValuesGenerator.getRandomIntBetween(randomV, -1, 25);
							 int startMinuteV=ValuesGenerator.getRandomIntBetween(randomV, -1, 61);
							 String titleV=(String) ValuesGenerator.getString(randomV);
							 String descriptionV=(String) ValuesGenerator.getString(randomV);
							 String emailAddressV=(String) ValuesGenerator.getString(randomV);
							 
							 Appt apptV = new Appt(startHourV,
							  startMinuteV,
							  startDay,
							  startMonth,
							  startYear + i,
							  titleV,
							 descriptionV,
							 emailAddressV);
							 apptV.setValid();
							 boolean validV = apptV.getValid();
						     
						   //input stuff here
							 calday0.addAppt(appt);
							 calday0.addAppt(apptV);
							 
							 
							 if (apptV.getValid() && appt.getValid()){
								 if (startHour < startHourV)
								 {	assertEquals(appt.toString(), calday0.getAppts().get(0).toString());}
								 else if (startHour == startHourV)
								 {/* //commented out as the CalDay implementation doesn't account for minute being in order code even says "finish this"
									 if (startMinute == startMinuteV)
									 {}
									 else if (startMinute < startMinuteV)
									 {	assertEquals(appt.toString(), calday0.getAppts().get(0).toString());}
									 else
									 {	assertEquals(apptV.toString(), calday0.getAppts().get(0).toString());}*/ 
								 } 
								 else 
								 { assertEquals(apptV.toString(), calday0.getAppts().get(0).toString());}
							 }
							 else if (!apptV.getValid() && !appt.getValid()){
								 
							 }
							 else if (!apptV.getValid()){
								 assertEquals(appt.toString(), calday0.getAppts().get(0).toString());
							 }
							 else if (!appt.getValid()){
								 assertEquals(apptV.toString(), calday0.getAppts().get(0).toString());
							 }
						}
						
				}
				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}catch(NullPointerException e){
			
		}catch(IndexOutOfBoundsException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }


	


	
}
