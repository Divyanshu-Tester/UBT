package test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base_package.Base_Class;

public class TC_005 extends Base_Class

{
	
  @Test
  public void f() 
  
  {
	 WebElement Products_Page =  driver.findElement(By.xpath("//a[normalize-space()='Products']"));
	 Products_Page.click();
	 String Get_title = driver.getTitle();
	 System.out.println("Current Page Title is : " +Get_title);
	  
	 if(Get_title == "Products ")
	 {
		 System.out.println("\nPage Title Correct/Page Opened");
	 }
	 else
	 {
		 System.out.println("\nPage Titleid not Correct or the page has not oppened \n ---RAISE THE BUG ---- ");
	 }
  }
}

