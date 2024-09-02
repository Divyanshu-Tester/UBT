package test_cases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base_package.Base_Class;

public class TC_008 extends Base_Class

{
  @Test
  public void Check_Social_Page_Title() 
  
  {
	  driver.findElement(By.xpath("//a[normalize-space()='Social']")).click();
	  String Social_Page_Title = driver.getTitle();
	  System.out.println("CURRENT PAGE TITLE :" +Social_Page_Title);
	  
	  if(Social_Page_Title == "Social")
	  {
		  System.out.println("PAGE TITLE MATCHED DON't RAISE THE BUG ");
	  }
	  else
	  {
		  System.out.println("PAGE HAS NOT OPENED OR THE TITLE HAS NOT MATCHED :: -- RAISE THE BUG ---");
	  }
  }
}
