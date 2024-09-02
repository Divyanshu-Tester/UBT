package test_cases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base_package.Base_Class;

public class TC_006 extends Base_Class


{
  @Test
  public void f() 
  
  {
	  driver.findElement(By.xpath("//a[normalize-space()='Research']")).click();
	  System.out.print("CURRENT PAGE TITLE :" +driver.getTitle());
	  String Service_Page_Title  = driver.getTitle();
	  
	  if(Service_Page_Title =="Service Page")
	  {
		  System.out.print("Page Title Matched : DONT RAISE THE BUG ");
		  
	  }
	  
	  else
	  {
		  System.out.println("PAGE TITLE DOES't MATCH / OR THE PAGE HAS NOT OPENED");
	  }
	  
	  
  }
  
  
}
