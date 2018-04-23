
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
	DataHandler data0 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2017, 9, 14);
	GregorianCalendar day2 = new GregorianCalendar(2017, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2017, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
    Appt appt3 = new Appt(20, 55, 14, 20, 2017, "10th Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
	appt3.setValid();
	data0.saveAppt(appt0);
	data0.saveAppt(appt3); //not supposed to save
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data0.getApptRange(day1,day2);
	CalDay calday= calDays.get(0);
	String str= calday.getFullInfomrationApp(calday);
	assertEquals("9-14-2017 ", str);
	
	data0.save();
	
  }
  
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
	try {DataHandler data0 = new DataHandler("calendar2.xml",true);
	GregorianCalendar day1 = new GregorianCalendar(2017, 11, 14);
	GregorianCalendar day2 = new GregorianCalendar(2017, 10, 25);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data0.getApptRange(day1,day2);
	fail("exception should have been thrown");
	}
	catch(DateOutOfRangeException e){}
	//should throw some exception "Second date specified is not before the first date specified."
	
  } 
  
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
	try{DataHandler data2 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2017, 9, 17);
	GregorianCalendar day2 = new GregorianCalendar(2017, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2017, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
	data2.saveAppt(appt0);
	data2.save();
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data2.getApptRange(day1,day2);
	LinkedList<Appt>  appts =calDays.get(0).getAppts();
	Appt appt_=appts.get(0);
	fail("exception should have been thrown");
	
	}
	catch(IndexOutOfBoundsException e){}
  }
  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
	try{DataHandler data3 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2017, 9, 14);
	GregorianCalendar day2 = new GregorianCalendar(2017, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2017, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
	data3.saveAppt(appt0);
	data3.save();
	data3.deleteAppt(appt0);
	data3.save();
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data3.getApptRange(day1,day2);
	LinkedList<Appt>  appts =calDays.get(0).getAppts();
	Appt appt_=appts.get(0);
	fail("exception should have been thrown, did not delete");
	
	}
	catch(IndexOutOfBoundsException e){}
  }
  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
	try{DataHandler data4 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2016, 9, 14);
	GregorianCalendar day2 = new GregorianCalendar(2016, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2016, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
	data4.saveAppt(appt0);
	data4.save();
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data4.getApptRange(day1,day2);
	LinkedList<Appt>  appts =calDays.get(0).getAppts();
	Appt appt_=appts.get(1);
	fail("exception should have been thrown, did not delete");
	
	}
	catch(IndexOutOfBoundsException e){}
  }
  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
	DataHandler data5 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2017, 9, 14);
	GregorianCalendar day2 = new GregorianCalendar(2017, 10, 25);
	CalDay calday0 = new CalDay(day1);
	CalDay calday1 = new CalDay(day2);
	Appt appt0 = new Appt(17, 30, 14, 9, 2017, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
    Appt appt3 = new Appt(20, 55, 14, 20, 2017, "10th Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
	appt3.setValid();
	data5.saveAppt(appt0);
	data5.saveAppt(appt3); //not supposed to save
	data5.save();
	assertFalse(data5.deleteAppt(appt3));
	
	
  }
 
  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
	DataHandler data6 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2015, 9, 14);
	GregorianCalendar day2 = new GregorianCalendar(2015, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2015, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	int[] recurDaysArr={0,1,2,6};
	appt0.setRecurrence(recurDaysArr, 1, 2, 1);
	appt0.setValid();
	data6.saveAppt(appt0);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data6.getApptRange(day1,day2);
	CalDay calday= calDays.get(7);
	String str= calday.getFullInfomrationApp(calday);
	assertEquals("9-21-2015 ", str);
	
	data6.save();
	
  }
  public void test07()  throws Throwable  {
	DataHandler data7 = null;
	GregorianCalendar day1 = new GregorianCalendar(2015, 9, 14);
	GregorianCalendar day2 = new GregorianCalendar(2015, 10, 25);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	assertNull(data7.getApptRange(day1,day2));
	
	data7.save();
	
  }
 
  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
	DataHandler data8 = new DataHandler();
	Appt appt0 = new Appt(17, 30, 14, 9, 2015, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
	assertFalse(data8.deleteAppt(appt0));
	
	data8.save();
	
  }
}