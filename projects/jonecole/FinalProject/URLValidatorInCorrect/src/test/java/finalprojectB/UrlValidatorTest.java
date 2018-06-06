
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {

   //public static final long allowAllSchemes = 1 << 0;

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	   
	   //UrlValidator urlVal = new UrlValidator(null, null, 1);
	   UrlValidator urlVal = new UrlValidator();
	   String url;
       boolean result;
	   
	   String[] URLs = {"www.google.com", "http://google.com", "ftp://google.com", "google.com/test/", "http://google.com:65535", "google.com:0/test1?action=view", "0.0.0.0", "255.255.255.255", "google.cn/test/test/action/$23",
						"goog.a12", "http:goo.au", ":60/test/", "http://google:60/test/", "", "ftp://google.com:-1", "google.com:65536/test", "google.com:20a/test", "china.cn/test/../"
						};
	   boolean[] URLcorrect = {true, true, true, true, true, true, true, true, true,
							   false, false, false, false, false, false, false, false, false
							   };
		
		for (int ii = 0; ii < URLs.length; ii++)
		{
			url = URLs[ii];
			result = urlVal.isValid(url);
		   if (result == URLcorrect[ii]) {
			  System.out.print(URLs[ii]+" matched expected result\n");
		   } else {
			  System.out.print(URLs[ii]+" did not match expected result\n");
		   }
		}
	   
   }
   
   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing	   

   }
   
   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing	   

   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
   


}
