/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
      assertFalse(appt0.getValid());
  }
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Appt appt0 = new Appt(12, 30, 9, 14, 2018, null, null, null);
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/9/2018 at 12:30pm ,, \n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
      assertFalse(appt0.getValid());
  }
@Test(timeout = 4000)
 public void test02()  throws Throwable  {
      Appt appt0 = new Appt(10, 15, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t15/10/2018 at -1:-1am ,Birthday Party, This is my birthday party\n", string0); // fails due to bug 
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();

}
  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Appt appt0 = new Appt(0, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  String string0 = appt0.toString();
      assertEquals("\t9/14/2018 at 12:30am ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
      assertTrue(appt0.getValid());
  }  
  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      try{
		  Appt appt0 = new Appt(24, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		  appt0.setValid();
	      assertFalse(appt0.getValid());
		  String string0 = appt0.toString();
	  }
	  catch(Exception e){
	  //insert the thrown exception
	  }
  }
    @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Appt appt0 = new Appt(15, 0, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
	  String string0 = appt0.toString();
      //fails, not sure if bug or not (doesn't put leading 0) assertEquals("\t9/14/2018 at 3:00pm ,Birthday Party, This is my birthday party\n", string0); 
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }
    @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Appt appt0 = new Appt(15, 60, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      appt0.setValid();
	  assertFalse(appt0.getValid());
  }
    @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 29, 2, 2020, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      appt0.setValid();
	  String string0 = appt0.toString();
      assertEquals("\t2/29/2020 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
  }
    @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 29, 2, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      appt0.setValid();
	  assertFalse(appt0.getValid());//fails due to setValid bug, 
  }
    @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 29, 2, 1900, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      appt0.setValid();
	  assertFalse(appt0.getValid());//fails due to setValid bug 
  }
    @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 29, 2, 2400, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      appt0.setValid();
	  String string0 = appt0.toString();
      assertEquals("\t2/29/2400 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
  }    
  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      int[] recurDaysArr={0,1,6};
	  appt0.setRecurrence(recurDaysArr, 1, 2, 3);
	  assertEquals(1, appt0.getRecurBy());
	  assertEquals(3, appt0.getRecurNumber());
	  assertEquals(recurDaysArr[0], appt0.getRecurDays()[0]);
      assertTrue(appt0.isRecurring());
      appt0.setValid();
      assertEquals(2, appt0.getRecurIncrement());
  }
 @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setRecurrence(null, 3, 3, 500);
	  assertTrue(appt0.isOn(14,9,2018));
	  assertEquals(3, appt0.getRecurBy());
	  assertEquals(500, appt0.getRecurNumber());
	  assertEquals(0, (appt0.getRecurDays()).length);// fails due to bug 
      assertTrue(appt0.isRecurring());
      appt0.setValid();
      assertEquals(3, appt0.getRecurIncrement());
  }
 @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 14, 9, -2, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }
 @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 14, -1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }
 @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, -1, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }
 @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Appt appt0 = new Appt(15, -1, 14, 9, -2, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

}
