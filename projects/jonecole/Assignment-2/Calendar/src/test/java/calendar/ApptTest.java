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
  }

  // month above valid amount, everything else fine; sets recurrence of appointment, checks recurrence
  @Test(timeout = 4000)
  public void test01()  throws Throwable {
      Appt appt1 = new Appt(13, 32, 2018, "Test Appt 1", "This is Test Appt 1", "xyz@gmail.com");
      String string1 = appt1.toString();
      int[] int1 = null;
      appt1.setRecurrence(int1,2,3,4);
      assertEquals(2, appt1.getRecurBy());
      assertTrue(appt1.isRecurring());
      assertEquals("\t32/13/2018 at -1:-1am ,Test Appt 1, This is Test Appt 1\n", string1);
      assertEquals(3, appt1.getRecurIncrement());
      appt1.setValid();
      string1 = appt1.toString();
      int[] int2 = appt1.getRecurDays();

      // check to see if time has been set
      assertFalse(appt1.hasTimeSet());

      // check to see if getXmlElement works
      assertEquals(null, appt1.getXmlElement());
  }

  // month below valid amount, everything else fine
  @Test(timeout = 4000)
  public void test02()  throws Throwable {
      Appt appt2 = new Appt(10, 30, 25, 0, 2018, "Test Appt 2", "This is Test Appt 2", "xyz@gmail.com");
      String string2 = appt2.toString();
      assertEquals("\t0/25/2018 at 10:30am ,Test Appt 2, This is Test Appt 2\n", string2);
      appt2.setValid();
  }

  // hour above valid amount, everything else fine
  @Test(timeout = 4000)
  public void test03()  throws Throwable {
      Appt appt3 = new Appt(25, 55, 25, 10, 2018, "Test Appt 3", "This is Test Appt 3", "xyz@gmail.com");
      String string3 = appt3.toString();
      assertEquals("\t10/25/2018 at 13:55pm ,Test Appt 3, This is Test Appt 3\n", string3);
      appt3.setValid();
  }

  // hour below valid amount, everything else fine
  @Test(timeout = 4000)
  public void test04()  throws Throwable {
      Appt appt4 = new Appt(-1, 55, 25, 10, 2018, "Test Appt 4", "This is Test Appt 4", "xyz@gmail.com");
      String string4 = appt4.toString();
      assertEquals("\t10/25/2018 at -1:55am ,Test Appt 4, This is Test Appt 4\n", string4);
      appt4.setValid();
  }

  // minute above valid amount, everything else fine
  @Test(timeout = 4000)
  public void test05()  throws Throwable {
      Appt appt5 = new Appt(5, 61, 25, 10, 2018, "Test Appt 5", "This is Test Appt 5", "xyz@gmail.com");
      String string5 = appt5.toString();
      assertEquals("\t10/25/2018 at 5:61am ,Test Appt 5, This is Test Appt 5\n", string5);
      appt5.setValid();
  }

  // minute below valid amount, everything else fine
  @Test(timeout = 4000)
  public void test06()  throws Throwable {
      Appt appt6 = new Appt(5, -1, 25, 10, 2018, "Test Appt 6", "This is Test Appt 6", "xyz@gmail.com");
      String string6 = appt6.toString();
      assertEquals("\t10/25/2018 at 5:-1am ,Test Appt 6, This is Test Appt 6\n", string6);
      appt6.setValid();
  }

  // minute below valid amount, everything else fine
  @Test(timeout = 4000)
  public void test07()  throws Throwable {
      Appt appt7 = new Appt(5, 45, 25, 10, 0, "Test Appt 7", "This is Test Appt 7", "xyz@gmail.com");
      String string7 = appt7.toString();
      assertEquals("\t10/25/0 at 5:45am ,Test Appt 7, This is Test Appt 7\n", string7);
      appt7.setValid();
  }

  // day above valid amount, everything else fine
  @Test(timeout = 4000)
  public void test08()  throws Throwable {
      Appt appt8 = new Appt(5, 45, 32, 10, 2018, "Test Appt 8", "This is Test Appt 8", "xyz@gmail.com");
      String string8 = appt8.toString();
      assertEquals("\t10/32/2018 at 5:45am ,Test Appt 8, This is Test Appt 8\n", string8);
      appt8.setValid();
  }

  // day below valid amount, everything else fine
  @Test(timeout = 4000)
  public void test09()  throws Throwable {
      Appt appt9 = new Appt(5, 45, 0, 10, 2018, "Test Appt 9", "This is Test Appt 9", "xyz@gmail.com");
      String string9 = appt9.toString();
      assertEquals("\t10/0/2018 at 5:45am ,Test Appt 9, This is Test Appt 9\n", string9);
      appt9.setValid();
  }

  // no title, desc, email, everything else fine; gets email address; checks the date it occurs
  @Test(timeout = 4000)
  public void test10()  throws Throwable {
      Appt appt10 = new Appt(5, 45, 20, 10, 2018, null, null, null);
      String string10 = appt10.toString();
      assertEquals("\t10/20/2018 at 5:45am ,, \n", string10);
      appt10.setValid();
      appt10.getEmailAddress();

      // first date check (4/25/2018); only year is correct
      assertFalse(appt10.isOn(25, 4, 2018));
      // second date check (10/21/2018); day is incorrect; bug here (should be false, but it's returning
      // true because of the bug that I introduced when I changed a '&&' to a '||'
      assertTrue(appt10.isOn(21, 10, 2018));
      // third date check (10/20/2018); correct date
      assertTrue(appt10.isOn(20, 10, 2018));
      // fourth date check (5/22/1997); correct date
      assertFalse(appt10.isOn(22, 5, 1997));

      // check to see if appointment time has been set
      assertTrue(appt10.hasTimeSet());
  }
}
