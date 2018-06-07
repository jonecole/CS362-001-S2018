
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
		System.out.print("\n\t\t\tTesting Manually\n");
	   //UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
	   UrlValidator urlVal = new UrlValidator();
	   String url;
       boolean result;
	   
	   String[] URLs = {"http://www.google.com", "http://google.com", "ftp://google.com", "http://google.com/test/", "http://google.com:65535", "http://google.com:0/test1?action=view", "http://0.0.0.0", "255.255.255.255", "http://google.cn/test/test/action/$23",
						"goog.a12", "http:goo.au", ":60/test/", "http://google:60/test/", "", "ftp://google.com:-1", "google.com:65536/test", "google.com:20a/test", "china.cn/test/../",
						null, "google.com" };
	   boolean[] URLCorrect = {true, true, true, true, true, true, true, false, true,
							   false, false, false, false, false, false, false, false, false,
							   false, false};
		
		for (int ii = 0; ii < URLs.length; ii++)
		{
			System.out.print("\n" + URLs[ii] + "\n\t");
			url = URLs[ii];
			
			try{
				result = urlVal.isValid(url);
				if (result == URLCorrect[ii]) {
					//System.out.print("PASS\n");
				} 
				else {
					System.out.print("FAIL\n");
					//assertEquals(url, URLCorrect[ii], result);
				}
			}
					
			catch(ExceptionInInitializerError e){
			    System.out.print("Error: ExceptionInInitializerError\n");
		    }
		    catch(NoClassDefFoundError e){
			    System.out.print("Error: NoClassDefFoundError\n");   
		    }
			
		}
	   
   }
   
   
   public void testYourFirstPartition()
   {//scheme
		
		System.out.print("\n\t\t\tTesting Schemes\n");
		UrlValidator urlVal = new UrlValidator();
		String url;
		boolean result;
   
   
		String[] Schemes = {"http://", "https://", "ftp://", "",
							"http", ":", "//", "htp://"};
		boolean[] SchemesCorrect = {true, true, true, false,
							 false, false, false, false};
		
		for (int ii = 0; ii < Schemes.length; ii++)
		{
			System.out.print("\n" +Schemes[ii] + "\n\t");
			url = Schemes[ii] + "google.com";
			
			try{
				result = urlVal.isValid(url);
				if (result == SchemesCorrect[ii]) {
					//System.out.print("PASS\n");
				} 
				else {
					System.out.print("FAIL\n");
					//assertEquals(url, SchemesCorrect[ii], result);
				}
			}
					
			catch(ExceptionInInitializerError e){
			    System.out.print("Error: ExceptionInInitializerError\n");
		    }
		    catch(NoClassDefFoundError e){
			    System.out.print("Error: NoClassDefFoundError\n");   
		    }
			
		}
		
		
   }
   
   public void testYourSecondPartition()
	{//authority
		
		System.out.print("\n\t\t\tTesting Authorities\n");
		UrlValidator urlVal = new UrlValidator();
		String url;
		boolean result;
   
   
		String[] Authority = {"google.com", "www.google.com", "google.cn", "0.0.0.0", "255.255.255.255",
							"google.123", "google", "google.a3343", "com.google.www", "-1.3.-20.255", ""};
		boolean[] AuthCorrect = {true, true, true, true, true,
							 false, false, false, false, false, false};
		
		for (int ii = 0; ii < Authority.length; ii++)
		{
			System.out.print("\n" +Authority[ii] + "\n\t");
			url = "http://" + Authority[ii];
			
			try{
				result = urlVal.isValid(url);
				if (result == AuthCorrect[ii]) {
					//System.out.print("PASS\n");
				} 
				else {
					System.out.print("FAIL\n");
					//assertEquals(url, AuthCorrect[ii], result);
				}
			}
					
			catch(ExceptionInInitializerError e){
			    System.out.print("Error: ExceptionInInitializerError\n");
		    }
		    catch(NoClassDefFoundError e){
			    System.out.print("Error: NoClassDefFoundError\n");   
		    }
			
		}
		

	}
	
	public void testYourThirdPartition()
	{//port
		
		System.out.print("\n\t\t\tTesting Ports\n");
		UrlValidator urlVal = new UrlValidator();
		String url;
		boolean result;
   
   
		String[] Port = {"", ":0", ":65535",
							":-1", ":a", ":60a"};
		boolean[] PortCorrect = {true, true, true,
							 false, false, false};
		
		for (int ii = 0; ii < Port.length; ii++)
		{
			System.out.print("\n" +Port[ii] + "\n\t");
			url = "http://google.com" + Port[ii];
			
			try{
				result = urlVal.isValid(url);
				if (result == PortCorrect[ii]) {
					//System.out.print("PASS\n");
				} 
				else 
				{
					System.out.print("FAIL\n");
					//assertEquals(url, PortCorrect[ii], result);
				}
			}
					
			catch(ExceptionInInitializerError e){
			    System.out.print("Error: ExceptionInInitializerError\n");
		    }
		    catch(NoClassDefFoundError e){
			    System.out.print("Error: NoClassDefFoundError\n");   
		    }
			
		}

		
		
	}
	
	public void testYourFourthPartition()
	{//path
		
		System.out.print("\n\t\t\tTesting Paths\n");
		UrlValidator urlVal = new UrlValidator();
		String url;
		boolean result;
   
   
		String[] Paths = {"", "/test/", "/test", "/t2",
							"5that", "//", "t2"};
		boolean[] PathCorrect = {true, true, true, true,
							 false, false, false};
		
		for (int ii = 0; ii < Paths.length; ii++)
		{
			System.out.print("\n" +Paths[ii] + "\n\t");
			url = "http://google.com" + Paths[ii];
			
			try{
				result = urlVal.isValid(url);
				if (result == PathCorrect[ii]) {
					//System.out.print("PASS\n");
				} 
				else 
				{
					System.out.print("FAIL\n");
					//assertEquals(url, PathCorrect[ii], result);
				}
			}
					
			catch(ExceptionInInitializerError e){
			    System.out.print("Error: ExceptionInInitializerError\n");
		    }
		    catch(NoClassDefFoundError e){
			    System.out.print("Error: NoClassDefFoundError\n");   
		    }
			
		}
		
	}
	
	public void testYourFifthPartition()
	{//query
		System.out.print("\n\t\t\tTesting Query\n");
		UrlValidator urlVal = new UrlValidator();
		String url;
		boolean result;
   
   
		String[] Querys = {"", "?action=view",
							"?action=nonsense", "?nonsense"};
		boolean[] QuerCorrect = {true, true,
							 true, true};
		
		for (int ii = 0; ii < Querys.length; ii++)
		{
			
			System.out.print("\n" +Querys[ii] + "\n\t");
			url = "http://google.com" + Querys[ii];
			
			try{
				result = urlVal.isValid(url);
				
				if (result == QuerCorrect[ii]) {
					//System.out.print("PASS\n");
				} 
				else 
				{
					System.out.print("FAIL\n");
					//assertEquals(url, QuerCorrect[ii], result);
				}
			}
					
			catch(ExceptionInInitializerError e){
			    System.out.print("Error: ExceptionInInitializerError\n");
		    }
		    catch(NoClassDefFoundError e){
			    System.out.print("Error: NoClassDefFoundError\n");   
		    }
			
			
		}

	}
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   
		System.out.print("\n\t\t\tTesting Programming\n");
		UrlValidator urlVal = new UrlValidator();
		String url;
		boolean result;
		
		String[] Schemes = {"http://", "https://", "ftp://", "",
							"http", ":", "//", "htp://"};
		boolean[] SchemesCorrect = {true, true, true, false,
							 false, false, false, false};
							 
		String[] Authority = {"google.com", "www.google.com", "google.cn", "0.0.0.0", "255.255.255.255",
							"google.123", "google", "google.a3343", "com.google.www", "-1.3.-20.255", ""};
		boolean[] AuthCorrect = {true, true, true, true, true,
							 false, false, false, false, false, false};
				
		String[] Port = {"", ":0", ":65535",
							":-1", ":a", ":60a"};
		boolean[] PortCorrect = {true, true, true,
							 false, false, false};

		String[] Paths = {"", "/test/", "/test", "/t2",
							"5that", "//", "t2"};
		boolean[] PathCorrect = {true, true, true, true,
							 false, false, false};
							 
		String[] Querys = {"", "?action=view",
							"?action=nonsense", "?nonsense"};
		boolean[] QuerCorrect = {true, true,
							 true, true};
		
		String[][] URL = new String[5][];
		URL[0] = Schemes;
		URL[1] = Authority;
		URL[2] = Port;
		URL[3] = Paths;
		URL[4] = Querys;
		
		boolean[][] Correct = new boolean[5][];
		Correct[0] = SchemesCorrect;
		Correct[1] = AuthCorrect;
		Correct[2] = PortCorrect;
		Correct[3] = PathCorrect;
		Correct[4] = QuerCorrect;
		
		int[] lengths = {Schemes.length, Authority.length, Port.length, Paths.length, Querys.length};
		int[] takePart = {0,0,0,0,0};
		
		boolean expected;
		boolean notDone = true;
		
		while(notDone)
		{
			url = URL[0][takePart[0]] +URL[1][takePart[1]] +URL[2][takePart[2]] +URL[3][takePart[3]] +URL[4][takePart[4]];
			expected = Correct[0][takePart[0]] && Correct[1][takePart[1]] && Correct[2][takePart[2]] && Correct[3][takePart[3]] && Correct[4][takePart[4]];
			
			try{
				result = urlVal.isValid(url);
				
				if (result == expected) {
					//System.out.print("PASS\n");
				} 
				else 
				{
					System.out.print("FAIL\n");
					//assertEquals(url, expected, result);
				}
			}
					
			catch(ExceptionInInitializerError e){
			    System.out.print("Error: ExceptionInInitializerError\n");
		    }
		    catch(NoClassDefFoundError e){
			    //System.out.print("Error: NoClassDefFoundError\n");   
		    }
			
			
			takePart[4]++;
			
			if (takePart[4] == lengths[4])
			{
					takePart[4] = 0;
					takePart[3]++;
			}
			if (takePart[3] == lengths[3])
			{
					takePart[3] = 0;
					takePart[2]++;
			}
			if (takePart[2] == lengths[2])
			{
					takePart[2] = 0;
					takePart[1]++;
			}
			if (takePart[1] == lengths[1])
			{
					takePart[1] = 0;
					takePart[0]++;
			}
			if (takePart[0] == lengths[0])
			{
				notDone = false;
			}
		};
   
	}

	
}
