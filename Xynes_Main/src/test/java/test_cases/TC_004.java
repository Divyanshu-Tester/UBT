package test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base_package.Base_Class;

public class TC_004 extends Base_Class


{
  @Test
  public void f() throws InterruptedException 
  
  {
	  
	  WebElement Service_Page = driver.findElement(By.xpath("//a[normalize-space()='Service']"));
	  Service_Page.click();
	  System.out.println("Current Page Title is : " +driver.getTitle());
	  String Page_Title = driver.getTitle();
	  
	  if(Page_Title == "Service")
	  {
		  System.out.println("TEST CASE PASS ");
	  }
	  
	  else
	  {
		  System.out.println("PAGE TITLE NOT MATCHED RAISE THE BUG ");
	  }
	  
  }
}
