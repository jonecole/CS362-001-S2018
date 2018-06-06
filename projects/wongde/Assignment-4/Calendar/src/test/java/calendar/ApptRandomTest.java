package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"setValid","setRecurDays","isOn"};// The list of the of methods to be tested in the Appt class

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
				
				 int startHour=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startMinute=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startDay=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startYear=ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
				 String title="Birthday Party";
				 String description="This is my birthday party.";
				 String emailAddress="xyz@gmail.com";

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

			 if(!appt.getValid())continue;
			for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					   if (methodName.equals("setValid")){
							 long randomseedV =System.currentTimeMillis(); //10
							 Random randomV = new Random(randomseedV);
							 boolean expected = true; 
							 int startHourV=ValuesGenerator.getRandomIntBetween(randomV, -1, 25);
							 if (startHourV < 0 || startHourV > 23)
							 {	expected = false;}
							 int startMinuteV=ValuesGenerator.getRandomIntBetween(randomV, -1, 61);
							 if (startMinuteV < 0 || startMinuteV > 59)
							 {	expected = false;}
							 int startDayV=ValuesGenerator.getRandomIntBetween(randomV, 0, 32);
							 if (startDayV < 1 || startDayV > 31)
							 {	expected = false;}
							 int startMonthV=ValuesGenerator.getRandomIntBetween(randomV, 0, 14);
							 if (startMonthV < 1 || startMonthV > 12)
							 {	expected = false;}
						     if ((startMonthV == 2 || startMonthV == 4 || startMonthV == 6 || startMonthV == 9 || startMonthV == 11) && startDayV > 30)
							 {	expected = false;}
							 int startYearV=ValuesGenerator.getRandomIntBetween(randomV, -1, 2019);
							 if (startYearV <= 0)
							 {	expected = false;}
							 if (startMonthV == 2)
							 {	
								if (startDayV > 29)
								{expected = false;}
								if(startYearV%4 != 0 && startDayV == 29)
								{expected = false;}
								else{
									if(startYearV%100 == 0 && startYearV%400 != 0 && startDayV == 29)
									{expected = false;}
								}
						     }
							 String titleV=(String) ValuesGenerator.getString(randomV);
							 String descriptionV=(String) ValuesGenerator.getString(randomV);
							 String emailAddressV=(String) ValuesGenerator.getString(randomV);
							 Appt apptV = new Appt(startHourV,
							  startMinuteV ,
							  startDayV ,
							  startMonthV ,
							  startYearV ,
							  titleV,
							 descriptionV,
							 emailAddressV);
							 apptV.setValid();
							 assertEquals(expected,apptV.getValid());//mute for coverage test 
						}
						else if (methodName.equals("setRecurDays")){
						   int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						   int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
						   int recur=ApptRandomTest.RandomSelectRecur(random);
						   int recurIncrement = ValuesGenerator.RandInt(random);
						   int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
						   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						   for (int ii = 0; ii < sizeArray; ii++)
						   {	
								assertEquals(recurDays[ii], (appt.getRecurDays())[ii]);//mute for coverage test 
						   }
						}
						else if (methodName.equals("isOn")){
							assertTrue(appt.isOn(startDay, startMonth, startYear));
							assertFalse(appt.isOn(startDay+1, startMonth, startYear));
							assertFalse(appt.isOn(startDay, startMonth+1, startYear));
							assertFalse(appt.isOn(startDay, startMonth, startYear+1));
						}		
				}
				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }


	


	
}
