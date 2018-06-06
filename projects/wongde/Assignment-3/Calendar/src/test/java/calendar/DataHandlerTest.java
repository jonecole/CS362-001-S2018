
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class DataHandlerTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
	DataHandler data0 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2017, 6, 14);
	GregorianCalendar day2 = new GregorianCalendar(2017, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 7, 2017, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
    Appt appt3 = new Appt(20, 55, 14, 20, 2017, "10th Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
	appt3.setValid();
	assertTrue(data0.saveAppt(appt0));
	assertFalse(data0.saveAppt(appt3)); //not supposed to save
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data0.getApptRange(day1,day2);
	CalDay calday= calDays.get(0);
	String str= calday.getFullInfomrationApp(calday);
	assertEquals("7-14-2017 \n\t5:30PM Birthday Party This is my birthday party ", str);
	
	data0.save();
	data0.deleteAppt(appt0);
	
  }
  
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
	try {DataHandler data0 = new DataHandler("cal.xml",true);
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
	try{DataHandler data2 = new DataHandler("cal.xml");
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
	try{DataHandler data3 = new DataHandler("cal.xml");
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
	try{DataHandler data4 = new DataHandler("cal.xml");
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
	data5.deleteAppt(appt0);
	
	
  }
 
  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
	DataHandler data6 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2015, 8, 14);
	GregorianCalendar day2 = new GregorianCalendar(2015, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2015, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	int[] recurDaysArr={0,1,2,6};
	appt0.setRecurrence(recurDaysArr, 1, 1, 1);
	appt0.setValid();
	data6.saveAppt(appt0);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data6.getApptRange(day1,day2);
	CalDay calday= calDays.get(7);
	String str= calday.getFullInfomrationApp(calday);
	assertEquals("9-21-2015 ", str);
	
	data6.save();
	data6.deleteAppt(appt0);
	
  }
  public void test07()  throws Throwable  {
	try{DataHandler data7 = null;
	GregorianCalendar day1 = new GregorianCalendar(2015, 9, 14);
	GregorianCalendar day2 = new GregorianCalendar(2015, 10, 25);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	assertNull(data7.getApptRange(day1,day2));
	
	data7.save();
	}
	catch(Exception e){fail("should not get here");}
  }
 
  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
	DataHandler data8 = new DataHandler();
	Appt appt0 = new Appt(17, 30, 14, 9, 2015, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
	assertFalse(data8.deleteAppt(appt0));
	
	data8.save();
	
  }
  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
	DataHandler data9 = new DataHandler("calendar2.xml",false);
	Appt appt0 = new Appt(17, 30, 14, 9, 2015, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
	assertFalse(data9.deleteAppt(appt0));
	
	data9.save();
	
  }
  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
	DataHandler data10 = new DataHandler("calendar2.xml",false);
	GregorianCalendar day1 = new GregorianCalendar(2017, 6, 14);
	GregorianCalendar day2 = new GregorianCalendar(2017, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 7, 2017, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
    Appt appt3 = new Appt(20, 55, 14, 20, 2017, "10th Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
	appt3.setValid();
	assertTrue(data10.saveAppt(appt0));
	assertFalse(data10.saveAppt(appt3)); //not supposed to save
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data10.getApptRange(day1,day2);
	CalDay calday= calDays.get(0);
	String str= calday.getFullInfomrationApp(calday);
	//is adding multiple assertEquals("7-14-2017 \n\t5:30PM Birthday Party This is my birthday party ", str);
	
	data10.save();
	data10.deleteAppt(appt0);
	
  }
  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
	DataHandler data11 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2013, 8, 14);
	GregorianCalendar day2 = new GregorianCalendar(2013, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2013, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setRecurrence(null, 1, 1, 1);
	appt0.setValid();
	data11.saveAppt(appt0);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data11.getApptRange(day1,day2);
	CalDay calday= calDays.get(7);
	String str= calday.getFullInfomrationApp(calday);
	assertEquals("9-21-2013 \n\t5:30PM Birthday Party This is my birthday party ", str);
	
	data11.save();
	data11.deleteAppt(appt0);
	
  }
  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
	DataHandler data12 = new DataHandler("calendar3.xml");
	GregorianCalendar day1 = new GregorianCalendar(2013, 8, 14);
	GregorianCalendar day2 = new GregorianCalendar(2014, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2013, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setRecurrence(null, 3, 1, 1);
	appt0.setValid();
	data12.saveAppt(appt0);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data12.getApptRange(day1,day2);
	CalDay calday= calDays.get(365);
	String str= calday.getFullInfomrationApp(calday);
	assertEquals("9-14-2014 \n\t5:30PM Birthday Party This is my birthday party ", str);
	
	data12.save();
	data12.deleteAppt(appt0);
	Appt appt1 = new Appt(17, 30, 14, 9, 2014, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	data12.deleteAppt(appt1);
  }
  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
	DataHandler data13 = new DataHandler("calendar0.xml");
	GregorianCalendar day1 = new GregorianCalendar(2013, 8, 14);
	GregorianCalendar day2 = new GregorianCalendar(2014, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2013, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setRecurrence(null, 3, 1, 1);
	appt0.setValid();
	data13.saveAppt(appt0);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data13.getApptRange(day1,day2);
	CalDay calday= calDays.get(365);
	String str= calday.getFullInfomrationApp(calday);
	assertEquals("9-14-2014 \n\t5:30PM Birthday Party This is my birthday party ", str);
	
	data13.save();
	//assertNull(appt0.getXmlElement());
	data13.deleteAppt(appt0);
	//assertNull(appt0.getXmlElement());
	Appt appt1 = new Appt(17, 30, 14, 9, 2014, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	data13.deleteAppt(appt1);
  }
  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
	DataHandler data14 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2013, 8, 14);
	GregorianCalendar day2 = new GregorianCalendar(2015, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2013, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setRecurrence(null, 3, 1, 2);
	appt0.setValid();
	data14.saveAppt(appt0);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data14.getApptRange(day1,day2);
	CalDay calday= calDays.get(730);
	String str= calday.getFullInfomrationApp(calday);
	assertEquals("9-14-2015 \n\t5:30PM Birthday Party This is my birthday party ", str);
	
	data14.save();
	data14.deleteAppt(appt0);
	Appt appt1 = new Appt(17, 30, 14, 9, 2014, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	data14.deleteAppt(appt1);
  }
  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
	DataHandler data15 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2013, 8, 14);
	GregorianCalendar day2 = new GregorianCalendar(2013, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2013, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setRecurrence(null, 2, 1, 2);
	appt0.setValid();
	data15.saveAppt(appt0);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data15.getApptRange(day1,day2);
	CalDay calday= calDays.get(61);
	String str= calday.getFullInfomrationApp(calday);
	assertEquals("11-14-2013 \n\t5:30PM Birthday Party This is my birthday party ", str);
	
	data15.save();
	data15.deleteAppt(appt0);
	Appt appt1 = new Appt(17, 30, 14, 11, 2013, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	data15.deleteAppt(appt1);
  }
  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
	ByteArrayOutputStream os = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(os);
	System.setOut(ps);
	
	DataHandler data16 = new DataHandler();
	GregorianCalendar day1 = new GregorianCalendar(2015, 8, 14);
	GregorianCalendar day2 = new GregorianCalendar(2015, 10, 25);
	Appt appt0 = new Appt(17, 30, 14, 9, 2015, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	int[] recurDaysArr={0,1,2,6};
	appt0.setRecurrence(recurDaysArr, 1, 1, 2);
	appt0.setValid();
	data16.saveAppt(appt0);
	LinkedList<CalDay> calDays = new LinkedList<CalDay>();
	calDays = (LinkedList<CalDay>) data16.getApptRange(day1,day2);
	CalDay calday= calDays.get(14);
	String str= calday.getFullInfomrationApp(calday);
	assertEquals("9-28-2015 ", str);
	CalDay calday1= calDays.get(3);
	String str1= calday.getFullInfomrationApp(calday1);
	assertEquals("9-17-2015 ", str1);
	CalDay calday2= calDays.get(4);
	String str2= calday.getFullInfomrationApp(calday2);
	assertEquals("9-18-2015 \n\t5:30PM Birthday Party This is my birthday party ", str2);
	CalDay calday3= calDays.get(8);
	String str3= calday.getFullInfomrationApp(calday3);
	assertEquals("9-22-2015 ", str3);
	
	data16.save();
	data16.deleteAppt(appt0);
	Appt appt1 = new Appt(17, 30, 18, 9, 2015, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	data16.deleteAppt(appt1);
	
	assertEquals("",os.toString());
	
	System.setOut(System.out);
	
  }
}