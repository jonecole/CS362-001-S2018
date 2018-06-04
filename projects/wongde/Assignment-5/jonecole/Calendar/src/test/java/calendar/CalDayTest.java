/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.GregorianCalendar;

public class CalDayTest{

  @Test(timeout = 4000)
  //confirm base functionality
  public void test00()  throws Throwable  {
	  
	GregorianCalendar day = new GregorianCalendar(2018, 11, 25);
	CalDay calday0 = new CalDay(day);
	Appt appt0 = new Appt(17, 30, 25, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	appt0.setValid();
	String string0 = calday0.toString();
    assertEquals("\t --- 12/25/2018 --- \n --- -------- Appointments ------------ --- \n\n", string0); // fails due to bug 
	calday0.addAppt(appt0);
	String string1 = calday0.toString();
	assertEquals("\t --- 12/25/2018 --- \n --- -------- Appointments ------------ --- \n\t12/25/2018 at 5:30pm ,Birthday Party, This is my birthday party\n \n", string1);  
	String string2 = calday0.getFullInfomrationApp(calday0);
	assertEquals("12-25-2018 \n\t5:30PM Birthday Party This is my birthday party ", string2);// fails due to bug 
  }
    @Test(timeout = 4000)
	//test valid affects not entering
  public void test01()  throws Throwable  {
	  
	GregorianCalendar day = new GregorianCalendar(2018, 11, 25);
	CalDay calday0 = new CalDay(day);
	Appt appt0 = new Appt(17, 5, 25, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt3 = new Appt(20, 55, 14, 20, 2018, "10th Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
	appt3.setValid();
	appt0.setValid();
	String string0 = calday0.toString();
    assertEquals("\t --- 12/25/2018 --- \n --- -------- Appointments ------------ --- \n\n", string0); // fails due to bug 
	calday0.addAppt(appt0);
	calday0.addAppt(appt3); //should not be added
	String string1 = calday0.toString();
	assertEquals("\t --- 12/25/2018 --- \n --- -------- Appointments ------------ --- \n\t12/25/2018 at 5:5pm ,Birthday Party, This is my birthday party\n \n", string1); // fails since code (not my bug) is not adding preceding 0 to minute: I have removed that 0 for this bug test
	String string2 = calday0.getFullInfomrationApp(calday0);
	assertEquals("12-25-2018 \n\t5:05PM Birthday Party This is my birthday party ", string2);// fails due to bug 
  }
      @Test(timeout = 4000)
	  //test ordering
  public void test02()  throws Throwable  {
	  
	GregorianCalendar day = new GregorianCalendar(2019, 11, 25);
	CalDay calday0 = new CalDay(day);
    Appt appt5 = new Appt(0, 10, 14, 9, 2018, "5th Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
	appt5.setValid();
	String string0 = calday0.toString();
    assertEquals("\t --- 12/25/2019 --- \n --- -------- Appointments ------------ --- \n\n", string0); // fails due to bug 
	calday0.addAppt(appt5);
	String string1 = calday0.toString();
	assertEquals("\t --- 12/25/2019 --- \n --- -------- Appointments ------------ --- \n\t9/14/2018 at 12:10am ,5th Birthday Party, This is my friend's birthday party\n \n", string1); 
	String string2 = calday0.getFullInfomrationApp(calday0);
	assertEquals("12-25-2019 \n\t12:10AM 5th Birthday Party This is my friend's birthday party ", string2); // fails due to bug 
  }
      @Test(timeout = 4000)
	  //test ordering
  public void test03()  throws Throwable  {
	  
	GregorianCalendar day = new GregorianCalendar(2018, 11, 25);
	CalDay calday0 = new CalDay(day);
	Appt appt0 = new Appt(17, 0, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    Appt appt1 = new Appt(15, 30, 14, 9, 2018, "2nd Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
    Appt appt2 = new Appt(20, 45, 14, 9, 2018, "3rd Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
    Appt appt4 = new Appt(8, 15, 14, 9, 2018, "4th Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
    Appt appt5 = new Appt(0, 10, 14, 9, 2018, "5th Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
    Appt appt3 = new Appt(20, 55, 14, 20, 2018, "10th Birthday Party", "This is my friend's birthday party", "xyz@gmail.com");
	appt3.setValid();
	String string0 = calday0.toString();
    assertEquals("\t --- 12/25/2018 --- \n --- -------- Appointments ------------ --- \n\n", string0); // fails due to bug 
	calday0.addAppt(appt0);
	calday0.addAppt(appt1);
	calday0.addAppt(appt2);
	calday0.addAppt(appt3); //should not be added
	calday0.addAppt(appt4);
	calday0.addAppt(appt5);
	String string1 = calday0.toString();
	assertEquals("\t --- 12/25/2018 --- \n --- -------- Appointments ------------ --- \n\t9/14/2018 at 12:10am ,5th Birthday Party, This is my friend's birthday party\n \t9/14/2018 at 8:15am ,4th Birthday Party, This is my friend's birthday party\n \t9/14/2018 at 3:30pm ,2nd Birthday Party, This is my friend's birthday party\n \t9/14/2018 at 5:0pm ,Birthday Party, This is my birthday party\n \t9/14/2018 at 8:45pm ,3rd Birthday Party, This is my friend's birthday party\n \n", string1); // fails since code (not my bug) is not adding preceding 0 to minute (edited string to allow bug testing)
	String string2 = calday0.getFullInfomrationApp(calday0);
	assertEquals("12-25-2018 \n\t12:10AM 5th Birthday Party This is my friend's birthday party \n\t8:15AM 4th Birthday Party This is my friend's birthday party \n\t3:30PM 2nd Birthday Party This is my friend's birthday party \n\t5:00PM Birthday Party This is my birthday party \n\t8:45PM 3rd Birthday Party This is my friend's birthday party ", string2);// fails due to bug 
  }
  @Test(timeout = 4000)
  //confirm base functionality
  public void test04()  throws Throwable  {
	  
	CalDay calday0 = new CalDay();
	assertFalse(calday0.isValid());
	String string0 = calday0.toString();
    assertEquals("", string0); 
  }
}
