package test_cases;

import org.testng.annotations.Test;

import base_package.Base_Class;

public class TC_001 extends Base_Class 

{
	
  @Test
  public void check_Title() 
  
  {
	  driver.getTitle();
	  System.out.print("This is the Page Title: "+driver.getTitle());
	  String Page_Title = driver.getTitle();
	  
	  if(Page_Title == "XYNES")
	  {
		  System.out.println("TEST CASE PASSED");
	  }
	  else
	  {
       
		  System.out.println("\nPage Title is Not Matching Raise the Bug");
		  
	  }
	  
	  
	  
	  
	  
	  
	  
	  
  }
  
}
